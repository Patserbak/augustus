package Model;

/**
 * Class representing a message.
 * 
 * @author Kevin Lavrijssen, Wout Mees, Florent Nander Meijer, Robbe Keters
 */
public class Message {

	private Label label; 
	private Party messageSentBy;
	private Party messageReicevedBy;
	private Message predecessor;
	private int order;
	private boolean selected;
	private int height = 1;
	private int width = 1;
	ResultMessage result = null;
	private int messageNumber;
	
	/**
	 * Constructor.
	 * 
	 * @param nameLabel		The new message's label name.
	 */
	public Message(String nameLabel) {
		this.label = new Label(nameLabel); 
	}
	
	/**
	 * Constructor for a clone of this message.
	 */
	@Override
	public Message clone() {
		Message returnMessage = null; 
		if( this.getClass() == ResultMessage.class) {
			returnMessage = new ResultMessage(this.getLabel().getLabelname());
		} else if( this.getClass() == InvocationMessage.class)	{
			returnMessage = new InvocationMessage(this.getLabel().getLabelname(),this.getResult());
		} else {
			return null;
		}
		returnMessage.setLabel(new Label(this.getLabel().getLabelname()));
		returnMessage.setSentBy(this.getSentBy());
		returnMessage.setReicevedBy(this.getReicevedBy());
		returnMessage.setPredecessor(this.getPredecessor());
		returnMessage.setOrder(this.getOrder());
		returnMessage.getLabel().setLabelPositionComm(label.getLabelPositionComm().getX(), label.getLabelPositionComm().getY());
		returnMessage.getLabel().setLabelPositionSeq(label.getLabelPositionSequence().getX(), label.getLabelPositionSequence().getY());
		returnMessage.setMessageNumber(this.getMessageNumber());
		returnMessage.setResult(this.getResult());
		return returnMessage;
	}
	
	/** 
	 * Returns this message's label.
	 * 
	 * @return This message's label.
	 */
	public Label getLabel() {
		return label;
	}
	
	/**
	 * Sets this message's label to the given label.
	 * 
	 * @param label		The given label.
	 */
	public void setLabel(Label label) {
		this.label = label;
	}
	
	/** 
	 * Returns this message's sending party.
	 * 
	 * @return This message's sending party.
	 */
	public Party getSentBy() {
		return messageSentBy;
	}
	
	/**
	 * Sets this message's sending party to the given sending party.
	 * 
	 * @param sentBy		The given sending party.
	 */
	public void setSentBy(Party sentBy) {
		this.messageSentBy = sentBy;
	}
	
	/** 
	 * Returns this message's receiving party.
	 * 
	 * @return This message's receiving party.
	 */
	public Party getReicevedBy() {
		return messageReicevedBy;
	}
	
	/**
	 * Sets this message's receiving party to the given receiving party.
	 * 
	 * @param reicevedBy		The given receivning party.
	 */
	public void setReicevedBy(Party reicevedBy) {
		this.messageReicevedBy = reicevedBy;
	}
	
	/**
	 * Sets this message's predecessing message to the given message.
	 * 
	 * @param predecessor		The given message.
	 */
	public void setPredecessor(Message predecessor){
		this.predecessor = predecessor;
	}
	
	/** 
	 * Returns this message's preceding message.
	 * 
	 * @return This message's preceding message.
	 */
	public Message getPredecessor(){
		return predecessor;
	}
	
	/**
	 * Returns this message's selection status.
	 * 
	 * @return This message's selection status.
	 */
	public Boolean getSelected() {
		return selected;
	}
	
	/**
	 * 
	 * Set this message's selection status to the given selection status.
	 * 
	 * @param selected		The given selection status.
	 */
	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
	
	/**
	 * Returns this message's width.
	 * 
	 * @return This message's width.
	 */
	public int getWidth(){
		return width;
	}
	
	/**
	 * Set this message's width to the given width.
	 * 
	 * @param w		The given width.
	 * @exception IllegalArgumentException		The given width is negative.
	 */
	public void setWidth(int w) throws IllegalArgumentException  {
		if(w<0) {throw new IllegalArgumentException("Negative Width");}
		width = w;
	}
	
	/**
	 * Returns this message's height.
	 * 
	 * @return This message's height.
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * Set this message's height to the given height.
	 * 
	 * @param h		The given height.
	 * @exception IllegalArgumentException		The given height is negative.
	 */
	public void setHeight(int h) throws IllegalArgumentException {
		if(h<0) {throw new IllegalArgumentException("Negative Height");}
		height = h;
	}
	
	/**
	 * Returns the order of this message in the stack of messages.
	 * 
	 * @return This message's order.
	 */
	public int getOrder() {
		return order;
	}
	
	/**
	 * 
	 * Sets the order of the message in the stack of messages to the given order.
	 * 
	 * @param o		The given order.
	 */
	public void setOrder(int o) {
		order = o;
	}
	
	/**
	 * 
	 * Returns this message's result message.
	 * 
	 * @return		This message's result message.
	 */
	public ResultMessage getResult() {
		return result;
	}
	
	/**
	 * 
	 * Sets this message's result message to the given result message.
	 * 
	 * @param m		The given result message.
	 */
	public void setResult(ResultMessage m) {
		result = m;
	}
	
	/**
	 * Returns this message's message number..
	 * 
	 * @return This message's message number.
	 */
	public int getMessageNumber() {
		return messageNumber;
	}
	
	/**
	 * 
	 * Sets this message's messag number to the given message number.
	 * 
	 * @param messageNumber		The given message number.
	 */
	public void setMessageNumber(int messageNumber) {
		this.messageNumber = messageNumber;
	}
	
}


