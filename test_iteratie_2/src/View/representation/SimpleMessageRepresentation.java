package View.representation;

import java.awt.Graphics;

import Model.Canvas;
import Model.Message;

public class SimpleMessageRepresentation implements Representation {

	protected Message message;
	
	public SimpleMessageRepresentation(Message m){
		this.message = m;
	}
	
	@Override
	public void draw(Canvas c, Graphics g) {}

}
