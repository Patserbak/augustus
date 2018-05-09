package View;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashSet;

import Model.Actor;
import Model.Canvas;
import Model.Message;
import Model.Party;
import Model.ResultMessage;
import View.representation.ActorRepresentation;
import View.representation.LabelRepresentation;
import View.representation.MessageRepresentation;
import View.representation.ObjectRepresentation;
import View.representation.PartyRepresentation;
import View.representation.Representation;
import View.representation.SeqActorRepresentation;
import View.representation.SeqInvocationMessageRepresentation;
import View.representation.SeqLabelRepresentation;
import View.representation.SeqMessageRepresentation;
import View.representation.SeqObjectRepresentation;
import View.representation.SeqPartyRepresentation;
import View.representation.SeqResultMessageRepresentation;
import View.representation.SeqSimpleMessageRepresentation;
import View.representation.SeqSimplePartyRepresentation;
import View.representation.SimplePartyRepresentation;

public class SequenceState implements ViewState {
	
	private Canvas canvas;
	private Graphics graphics;
	
	/**
	 * draw the basic 
	 */
	@Override
	public void draw(ViewContext viewContext, Canvas canvas, Graphics graphics) {
		this.canvas = canvas;
		this.graphics = graphics;
		
		drawSubWindow();
		drawPartiesBox();
		
		drawParties();
		drawMessages();
	}
	
	/**
	 * Draws the subwindow.
	 */
	private void drawSubWindow() {
		graphics.setClip(canvas.getOrigineX(), canvas.getOrigineY(), canvas.getWidth(), canvas.getHeight());
		
		graphics.setColor(Color.WHITE);
		graphics.fillRect(canvas.getOrigineX(), canvas.getOrigineY(), canvas.getWidth(), canvas.getHeight());
		graphics.setColor(Color.BLACK);

		canvas.getFramework().draw(canvas, graphics);
	}
	
	/**
	 * draws the box in which parties resign.
	 */
	private void drawPartiesBox() {
		graphics.drawRect(canvas.getOrigineX()+10,  canvas.getOrigineY()+ 20, canvas.getWidth()-20,10+ canvas.getHeight()/6);
		graphics.drawString("Parties", canvas.getOrigineX()+ 20, canvas.getOrigineY()+ 40);
	}

	/**
	 * 
	 */
	private void drawParties() {
		// TODO:
		// Better way of getting which type of party it is.
		
		for(Party p : canvas.getParties()) {
			PartyRepresentation partyRep;
			
			if (p.getClass().equals(Actor.class)) {
				partyRep = new SeqActorRepresentation(p, new SeqPartyRepresentation(p, new SeqSimplePartyRepresentation(p)));
				
			} else 
				partyRep = new SeqObjectRepresentation(p, new SeqPartyRepresentation(p, new SeqSimplePartyRepresentation(p)));

			partyRep.draw(canvas, graphics);
			drawLabel(p);
			
		}
	}

	private void drawMessages() {
		for(Message m : canvas.getSortedMessages()){
			MessageRepresentation messageRep;
			if(m.getClass() != ResultMessage.class) {
				messageRep = new SeqInvocationMessageRepresentation(m, new SeqMessageRepresentation(m, new SeqSimpleMessageRepresentation(m)));
				drawLabel(m);
			} else {
				messageRep = new SeqResultMessageRepresentation(m, new SeqMessageRepresentation(m, new SeqSimpleMessageRepresentation(m)));
			}
			
			messageRep.draw(canvas, graphics);
		}
		
	}
	
	private void drawLabel(Party p) {
		LabelRepresentation labelRep = new SeqLabelRepresentation(p.getLabel());
		labelRep.draw(canvas, graphics);
	}
	
	private void drawLabel(Message m) {
		LabelRepresentation labelRep = new SeqLabelRepresentation(m.getLabel());
		labelRep.draw(canvas, graphics);
	}
	
}
