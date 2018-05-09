package View.representation;

import java.awt.Graphics;

import Model.Canvas;
import Model.Party;

public class SimplePartyRepresentation implements Representation{

	protected Party party;
	
	public SimplePartyRepresentation(Party p) {
		this.party = p;
	}
	
	public void draw(Canvas c, Graphics g) {}
	
	
}
