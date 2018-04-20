package Model;
/**
 * Label of a message or party
 * 
 * @author Kevin Lavrijssen, Wout Mees, Florent Nander Meijer, Robbe Keters
 */
public class Label {
	
	private String labelname;
	private Point labelPositionSeq;
	private Point labelPositionComm;
	private boolean selected;
	private int width = 50;
	private int height = 20;
	
	/**
	 * 
	 * Constructor.
	 * 
	 * @param labelName		The new label's name.
	 */
	public Label(String labelName) {
		this.labelname = labelName;
		this.labelPositionSeq = new Point(5,5);
		this.labelPositionComm = new Point(5,5);
	}
	
	/**
	 * 
	 * Returns this label's name.
	 * 
	 * @return		This label's name.
	 */
	public String getLabelname() {
		return labelname;
	}
	
	/**
	 * Sets this label's name to a given name.
	 * 
	 * @param labelname		The given name.
	 */
	public void setLabelname(String labelname) {
		this.labelname = labelname;
	}
	
	/**
	 * Returns this label's position in the sequence diagram.
	 * 
	 * @return		This label's position in the sequence diagram.
	 */
	public Point getLabelPositionSequence() {
		return labelPositionSeq;
	}
	
	/**
	 * 
	 * Sets this label's position in the sequence diagram to the given position.
	 * 
	 * @param labelPositionSeq		The given position.
	 */
	public void setLabelPositionSeq(Point labelPositionSeq) {
		this.labelPositionSeq = labelPositionSeq;
	}
	
	/**
	 * 
	 * Sets this label's position in the sequence diagram to the given x and y coordinates.
	 * 
	 * @param x		The given x coordinate.
	 * @param y		The given y coordinate.
	 */
	public void setLabelPositionSeq(int x, int y) {
		labelPositionSeq.xCoordinate = x;
		labelPositionSeq.yCoordinate = y;
	}
	
	/**
	 * Returns this label's position in the communication diagram.
	 * 
	 * @return		This label's position in the communication diagram.
	 */
	public Point getLabelPositionComm() {
		return labelPositionComm;
	}
	
	/**
	 * 
	 * Sets this label's position in the communication diagram to the given position.
	 * 
	 * @param labelPositionComm		The given position.
	 */
	public void setLabelPositionComm(Point labelPositionComm) {
		this.labelPositionComm = labelPositionComm;
	}
	
	/**
	 * 
	 * Sets this label's position in the communication diagram to the given x and y coordinates.
	 * 
	 * @param x		The given x coordinate.
	 * @param y		The given y coordinate.
	 */
	public void setLabelPositionComm(int x, int y) {
		labelPositionComm.xCoordinate = x;
		labelPositionComm.yCoordinate = y;
	}
	
	/**
	 * 
	 * Returns whether or not this label is selected.
	 * 
	 * @return		This label's selection status.
	 */
	public Boolean getSelected() {
		return selected;
	}
	
	/**
	 * Sets this label's selection status to the given selection status.
	 * 
	 * @param selected		The given selection status.
	 */
	public void setSelected(Boolean selected) {
		this.selected = selected;
		if(!selected) {
			if (labelname.length() == 0) {}
			else if (labelname.charAt(labelname.length()-1)=='|') {
				labelname = labelname.substring(0, (labelname.length()-1));
			}
		}
		else{labelname = labelname+"|";}
	}
	
	/**
	 * Returns this label's width.
	 * 
	 * @return		This label's width.
	 */
	public int getWidth(){
		return width;
	}
	
	/**
	 * Sets this label's width to the given width.
	 * 
	 * @param w								The given width.
	 * @throws IllegalArgumentException		The given width is negative.
	 */
	public void setWidth(int w) throws IllegalArgumentException  {
		if(w<0) {throw new IllegalArgumentException("Negative Width");}
		width = w;
	}
	
	/**
	 * 
	 * Returns this label's height.
	 * 
	 * @return		This label's height.
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * Sets this label's height to the given height.
	 * 
	 * @param h								The given height.
	 * @throws IllegalArgumentException		The given height is negative.
	 */
	public void setHeight(int h) throws IllegalArgumentException {
		if(h<0) {throw new IllegalArgumentException("Negative Height");}
		height = h;
	}
	
}
