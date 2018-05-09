package View.representation;

import java.awt.Graphics;

import Model.Canvas;
import Model.Party;

public class ComSimplePartyRepresentation extends SimplePartyRepresentation {


	public ComSimplePartyRepresentation(Party p) {
		super(p);
	}
	
	public void draw(Canvas c, Graphics g) {
		drawRectangle(g);
	}
	
	private void drawRectangle(Graphics g) {
		g.drawRect(	party.getPosComm().getX() - party.getLabel().getWidth()/2, 
					party.getPosComm().getY() - party.getLabel().getHeight()/2, 
					party.getLabel().getWidth(), 
					party.getLabel().getHeight());
	}

}
