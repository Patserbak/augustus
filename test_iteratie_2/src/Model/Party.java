package Model;

import java.util.Random;

/**
 * An system or human actor that sends and receives messages from other parties.
 */
public abstract class Party {

	private String className; 
	private Point posSeq;
	private Point posComm;
	private boolean selected;
	private Label label;
	private int height = 30;
	private int width = 70;
	private PartyRole role = PartyRole.NONE;
	private Random randNumberPos;
	private  int partyNumber;
	private int selectedYPosition;

	
	/**
	 * Constructor for a party
	 * @param cName		The new party's name.
	 */
	protected Party (String cName,int partyNumber){
		this.className = cName;

		randNumberPos = new Random();
			
		int xNew = randNumberPos.nextInt(600) + 20;
		int yNew = randNumberPos.nextInt(600) + 20;
		
		this.posComm = new Point(xNew, yNew); // default position
		this.posSeq = new Point(0,0); //default position
		this.partyNumber = partyNumber;
	}
	@Override
	public Party clone() {
		Party returnParty = null;
		if( Object.class == this.getClass()) {
			returnParty = new Object( this.getClassName(),this.partyNumber);
		} else if (Actor.class == this.getClass()){
			returnParty = new Actor( this.getClassName(),this.partyNumber);

		} else {
			return null;
		}
		returnParty.setPosComm(this.getPosComm().getX(), this.getPosComm().getY());
		returnParty.setPosSeq(this.getPosSeq().getX(), this.getPosSeq().getY());
		
		Label label = new Label(this.getLabel().getLabelname());
		label.setWidth(this.getLabel().getWidth());
		label.setLabelPositionSeq(this.getPosSeq().getX(), this.getPosSeq().getY() + this.getHeight() + 10);
		label.setLabelPositionComm(this.getPosComm().getX(), this.getPosComm().getY());
		returnParty.setLabel(label);
		
		return returnParty;
	}
	
	/**
	 * Returns the name of this party.
	 * @return		The party's name.
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * Returns the position of this party in the sequence diagram.
	 * @return		The party's position in the sequence diagram.
	 */
	public Point getPosSeq() {
		return posSeq;
	}
	
	/**
	 * Sets the position of this party in the sequence diagram to the given position.
	 * @param x		The given x coordinate.
	 * @param y		The given y coordinate.
	 */
	public void setPosSeq(int x, int y ) {
		this.posSeq = new Point(x,y);
	}
	
	/**
	 * Returns the position of this party in the communication diagram.
	 * @return		The party's position in the communication diagram.
	 */
	public Point getPosComm() {
		return posComm;
	}
	
	/**
	 * Sets the position of this party in the communication diagram to the given position.
	 * @param x		The given x coordinate.
	 * @param y		The given y coordinate.
	 */
	public void setPosComm(int x, int y) {
		this.posComm = new Point(x,y);
	}
	
	/**
	 * Sets the name of this party's class.
	 * @param className		The given class name.
	 */
	public void setClassName(String className) {
		this.className = className;
	}
	
	/**
	 * Returns whether or not this party is selected.
	 * @return		This party's status of selection.
	 */
	public Boolean getSelected() {
		return selected;
	}
	
	/**
	 * Sets this party's selection status to the given selection status.
	 * @param selected		The given selection status.
	 */
	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
	
	/**
	 * Returns this party's label.
	 * @return		This party's label.
	 */
	public Label getLabel() {
		return label;
	}
	
	/**
	 * Sets this party's label to the given label.
	 * @param label		The given label.
	 */
	public void setLabel(Label label) {
		this.label = label;
	}
	
	/**
	 * Returns this party's width.
	 * @return		This party's width.
	 */
	public int getWidth(){
		return width;
	}
	
	/**
	 * Sets this party's width to the given width.
	 * @param w		The given width.
	 * @throws IllegalArgumentException		The given width is negative.
	 */
	public void setWidth(int w) throws IllegalArgumentException  {
		if(w<0) {throw new IllegalArgumentException("Negative Width");}
		width = w;
	}
	
	/**
	 * Returns this party's height.
	 * @return		This party's width.
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * Sets this party's height to the given height.
	 * @param h		The given height.
	 * @throws IllegalArgumentException		The given height is negative.
	 */
	public void setHeight(int h) throws IllegalArgumentException {
		if(h<0) {throw new IllegalArgumentException("Negative Height");}
		height = h;
	}
	
	/**
	 * Returns the role of this party (sender, receiver or neither)
	 * @return		This party's role.
	 */
	public PartyRole getRole() {
		return role;
	}
	
	/**
	 * Make this party the message sender.
	 */
	public void makeSender() {
		role = PartyRole.SENDER;
	}
	
	/**
	 * Make this party the message receiver.
	 */
	public void makeReceiver() {
		role = PartyRole.RECEIVER;
	}
	
	/**
	 * Make this party neither the message sender or the message receiver.
	 */
	public void makeNone() {
		role = PartyRole.NONE;
	}

	/**
	 * Returns the selected Y position.
	 * @return 		Selected Y position.
	 */
	public int getSelectedYPosition() {
		return selectedYPosition;
	}

	/**
	 * Sets the selected Y position to a given value.
	 * @param selectedYPosition 	The value to which selected Y position should be set.
	 */
	public void setSelectedYPosition(int selectedYPosition) {
		this.selectedYPosition = selectedYPosition;
	}

	/**
	 * Returns this Party's number.
	 * @return	The number of this party.
	 */
	public int getPartyNumber() {
		return partyNumber;
	}
	
}
