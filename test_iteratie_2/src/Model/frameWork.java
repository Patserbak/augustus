package Model;

import java.awt.Graphics;

/**
 * A framework used to represent a subwindow visually.
 * 
 * @author Kevin Lavrijssen, Wout Mees, Florent Nander Meijer, Robbe Keters
 */
public class frameWork {
	
	private titleBar bar;
	private int origineX;
	private int origineY;
	
	/**
	 * Constructor.
	 * @param origineX		The x coordinate of origin of the new framework.
	 * @param origineY		The y coordinate of origin of the new framework.
	 */
	public frameWork(int origineX , int origineY) {
		this.bar= new titleBar(origineX,origineY);
		this.origineX = origineX;
		this.origineY = origineY;
	}
	
	/**
	 * Draws a visual representation of a given canvas's framework.
	 * @param c		The given canvas.
	 * @param g		The graphics object used to draw.
	 */
	public void draw(Canvas c, Graphics g) {
		
		// draw framework
		this.origineX = c.getOrigineX();
		this.origineY = c.getOrigineY();
		
	    g.drawRect(origineX, origineY, c.getWidth()-1, c.getHeight()-1);

	    bar.draw(c, g);
		
	}
	
	/**
	 * Returns this framwork's title bar.
	 * @return This framwork's title bar.
	 */
	public titleBar getBar() {
		return bar;
	}
	
	/**
	 * Sets this framework's title bar to the given title bar.
	 * @param bar	The given title bar.
	 */
	public void setBar(titleBar bar) {
		this.bar = bar;
	}
	
	/**
	 * Returns this framwork's x coordinate of origin.
	 * @return This framwork's x coordinate of origin.
	 */
	public int getOrigineX() {
		return origineX;
	}
	
	/**
	 * Sets this framework's x coordinate of origin to the given x coordinate.
	 * @param origineX	The given x coordinate.
	 */
	public void setOrigineX(int origineX) {
		this.origineX = origineX;
	}
	
	/**
	 * Returns this framwork's y coordinate of origin.
	 * @return This framwork's y coordinate of origin.
	 */
	public int getOrigineY() {
		return origineY;
	}
	
	/**
	 * Sets this framework's y coordinate of origin to the given y coordinate.
	 * @param origineY	The given y coordinate.
	 */
	public void setOrigineY(int origineY) {
		this.origineY = origineY;
	}
	
}
