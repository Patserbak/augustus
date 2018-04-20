package Model;

/**
 * A class used to store the coordinate of a mouse event or an element.
 * 
 * @author Kevin Lavrijssen, Wout Mees, Florent Nander Meijer, Robbe Keters
 */
public class Point {
	
	/**
	 * The x coordinate.
	 */
	public int xCoordinate;
	
	/**
	 * The y coordinate.
	 */
	public int yCoordinate;
	
	/**
	 * The constructor for a point.
	 * @param xCor		This new point's x coordinate.
	 * @param yCor		This new point's y coordinate.
	 */
	public Point(int xCor, int yCor) {
		this.xCoordinate = xCor;
		this.yCoordinate = yCor; 
	}
	
	/**
	 * Returns this point's x coordinate.
	 * @return		This point's x coordinate.
	 */
	public int getX() {
		return this.xCoordinate;
	}
	
	/**
	 * Returns this point's y coordinate.
	 * @return		This point's y coordinate.
	 */
	public int getY() {
		return this.yCoordinate;
	}

}
