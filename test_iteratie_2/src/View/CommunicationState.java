package View;

import java.awt.Color;
import java.awt.Graphics;

import Model.Actor;
import Model.Canvas;
import Model.Party;
import View.representation.ComActorRepresentation;
import View.representation.ComLabelRepresentation;
import View.representation.ComObjectRepresentation;
import View.representation.ComSimplePartyRepresentation;
import View.representation.LabelRepresentation;
import View.representation.Representation;
import View.representation.SimplePartyRepresentation;

public class CommunicationState implements ViewState {
	
	private Canvas canvas;
	private Graphics graphics;
	
	@Override
	public void draw(ViewContext viewContext, Canvas canvas, Graphics graphics) {
		this.canvas = canvas;
		this.graphics = graphics;
		
		drawSubWindow();
		
		drawParties();
		drawMessages();
	}
	
	private void drawSubWindow() {
		graphics.setClip(canvas.getOrigineX(), canvas.getOrigineY(), canvas.getWidth(), canvas.getHeight());
		
		graphics.setColor(Color.WHITE);
		graphics.fillRect(canvas.getOrigineX(), canvas.getOrigineY(), canvas.getWidth(), canvas.getHeight());
		graphics.setColor(Color.BLACK);

		canvas.getFramework().draw(canvas, graphics);
	}

	private void drawParties() {
		
		for(Party p : canvas.getParties()) {
			Representation partyRep;
			
			// TODO:
			// Better way of getting which party it is.
			if (p.getClass().equals(Actor.class)) 
				partyRep = new ComActorRepresentation(p, new ComSimplePartyRepresentation(p));
				
			 else 
				partyRep = new ComObjectRepresentation(p, new ComSimplePartyRepresentation(p));
			
			partyRep.draw(canvas, graphics);
			drawLabel(p);
			
		}
	}

	private void drawMessages() {
		// TODO Auto-generated method stub
		
	}
	
	private void drawLabel(Party p) {
		LabelRepresentation labelRep = new ComLabelRepresentation(p.getLabel());
		labelRep.draw(canvas, graphics);
	}
}
