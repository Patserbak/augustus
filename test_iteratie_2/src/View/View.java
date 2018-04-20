package View;

import java.awt.Graphics;

import Model.Canvas;
/**
 * 
 * Visual representation of a canvas.
 * 
 * @author Kevin Lavrijssen, Wout Mees, Florent Nander Meijer, Robbe Keters
 *
 */
public abstract class View {
	/**
	 * 
	 * Draws a visual represntation of a given canvas.
	 * 
	 * @param canvas		The canvas to draw.
	 * @param g				The graphics object used for drawing.
	 */
	public void draw(Canvas canvas, Graphics g) {}
	
}
