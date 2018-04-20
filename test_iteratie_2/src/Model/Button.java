package Model;

import java.awt.Color;
import java.awt.Graphics;

/**
 * A closing button used to close a subwindow.
 * 
 * @author Kevin Lavrijssen, Wout Mees, Florent Nander Meijer, Robbe Keters
 */
public class Button {
	
	private int width;
	private int height;
	private int OrigineX;
	private int OrigineY;
	
	/**
	 * Constructor.
	 * @param xInput	The x coordinate of the origin of the new button.
	 * @param yInput	The y coordinate of the origin of the new button.
	 */
	public Button(int xInput,int yInput) {
		this.OrigineX = xInput;
		this.OrigineY =yInput;
		this.width = 14;
		this.height = 14;
	}
	
	/**
	 * Return this button's width.
	 * @return	This button's width.
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Sets this button's width to the given width.
	 * @param width		The given width.
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	
	/**
	 * Return this button's height.
	 * @return		This button's height.
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * Sets this button's height to the given height.
	 * @param height	The given height.
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	
	/**
	 * Sraws a visual representation of this button.
	 * @param g		The graphics object used to draw.
	 */
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(OrigineX , OrigineY, width, height);
		g.setColor(Color.BLACK);
		g.drawRect(OrigineX , OrigineY, width, height);
		g.drawLine(OrigineX, OrigineY, OrigineX+ width, OrigineY +height);
		g.drawLine(OrigineX , OrigineY + height , OrigineX + width , OrigineY);
	}
	
	/**
	 * Return this button's x coordinate of origin.
	 * @return		This button's x coordinate of origin.
	 */
	public int getOrigineX() {
		return OrigineX;
	}
	
	/**
	 * Sets this button's x coordinate of origin to the given x coordinate.
	 * @param origineX		The given x coordinate.
	 */
	public void setOrigineX(int origineX) {
		OrigineX = origineX;
	}
	
	/**
	 * Return this button's y coordinate of origin.
	 * @return		This button's y coordinate of origin.
	 */
	public int getOrigineY() {
		return OrigineY;
	}
	
	/**
	 * Sets this button's y coordinate of origin to the given y coordinate.
	 * @param origineY		The given y coordinate.
	 */
	public void setOrigineY(int origineY) {
		OrigineY = origineY;
	}
	
}
