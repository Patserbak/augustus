package Model;

import java.awt.Color;
import java.awt.Graphics;

/**
 * A title bar used to drag a subwindow around.
 * 
 * @author Kevin Lavrijssen, Wout Mees, Florent Nander Meijer, Robbe Keters
 */
public class titleBar {
	
	private String title = "No title";
	private Button button;
	private int OrigineX;
	private int OrigineY;
	static int height = 20;
	private int width = 8*title.length();
	
	/**
	 * Constructor.
	 * @param xInput	The x coordinate of origin of the new title bar.
	 * @param yInput	The y coordinate of origin of the new title bar.
	 */
	public titleBar(int xInput, int yInput) {
		this.OrigineX = xInput;
		this.OrigineY = yInput;
		this.button = new Button(OrigineX +3 , OrigineY +3);
	}
	
	/**
	 * Returns this title bar's title.
	 * @return This title bar's title.
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Sets this title bar's title to the given title.
	 * @param title		The given title.
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * Returns this title bar's button.
	 * @return This title bar's button.
	 */
	public Button getButton() {
		return button;
	}
	
	/**
	 * Sets this title bar's button to the given button.
	 * @param button		The given button.
	 */
	public void setButton(Button button) {
		this.button = button;
	}
	
	/**
	 * Draws a visual representation of this title bar belonging to the given canvas.
	 * @param c		The given canvas.
	 * @param g		The graphics object used to draw.
	 */
	public void draw(Canvas c, Graphics g) {

		if (c.getView() == Canvas.View.SEQUENCE) {
			title = "Sequence Diagram";
		} else  {
			title = "Communication Diagram";
		}
		
		this.OrigineX = c.getOrigineX();
		this.OrigineY = c.getOrigineY();
		
		// Title Bar
		g.setColor(Color.DARK_GRAY);
		g.fillRect(OrigineX, OrigineY, c.getWidth(), height);
		g.setColor(Color.BLACK);
		g.drawRect(OrigineX, OrigineY, c.getWidth(), height);
		g.drawString(title, OrigineX + 20 , OrigineY + 14);
		
		button.setOrigineX(OrigineX);
		button.setOrigineY(OrigineY);
		button.draw(g);

	}
	
	/**
	 * Returns this title bar's x coordinate of origin.
	 * @return This title bar's x coordinate of origin.
	 */
	public int getOrigineX() {
		return OrigineX;
	}
	
	/**
	 * Sets this title bar's x coordinate of origin to the given x coordinate.
	 * @param origineX		The given x coordinate.
	 */
	public void setOrigineX(int origineX) {
		OrigineX = origineX;
	}
	
	/**
	 * Returns this title bar's y coordinate of origin.
	 * @return This title bar's y coordinate of origin.
	 */
	public int getOrigineY() {
		return OrigineY;
	}
	
	/**
	 * Sets this title bar's y coordinate of origin to the given y coordinate.
	 * @param origineY		The given y coordinate.
	 */
	public void setOrigineY(int origineY) {
		OrigineY = origineY;
	}
	
	
	/**
	 * Returns the width of a given canvas.
	 * @param c		The given canvas.
	 * @return		The given canvas's width.
	 */
	public int getWidth(Canvas c) {
		// Longest String (Switching views....)
		return c.getWidth();
	}
	
	/**
	 * Returns this title bar's height.
	 * @return This title bar's height.
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * Returns this title bar's minimum width.
	 * @return	This title bar's minimum width.
	 */
	public int getMinimumWidth() {
		return 8*"Communication Diagram".length(); 
	}
}
