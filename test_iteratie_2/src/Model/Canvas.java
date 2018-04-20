package Model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;

import Model.Handler.AddMessageHandler;

/**
 * A system of parties and messages.
 * 
 * @author Kevin Lavrijssen, Wout Mees, Florent Nander Meijer, Robbe Keters
 */
public class Canvas {

	private HashSet<Party> parties;
	private HashSet<Message> messages;
	
	private int width;
	private int height;
	private ArrayList<ResultMessage> resultQueue = new ArrayList<ResultMessage>();
	private Mode mode = Mode.DEFAULT;
	private View view = View.SEQUENCE;
	private int origineX;
	private int origineY;
	private frameWork framework;
	private Interaction interaction = null; 
	public boolean updated = false;
	
	/**
	 * Constructor.
	 * 
	 * @param width		The new canvas's wodth.
	 * @param height	The new canvas's height.
	 * @param origineX	The new canvas's x coordinate of origin.
	 * @param origineY	The new canvas's y coordinate of origin.
	 * @param i			The new canvas's interaction.
	 */
	public Canvas(int width, int height, int origineX, int origineY, Interaction i) {
		this.parties = new HashSet<Party>();
		this.messages = new HashSet<Message>();
		this.width = width;
		this.height = height;
		this.framework = new frameWork(origineX,origineY);
		this.interaction = i;
		this.origineX= origineX;
		this.origineY= origineY;
	}
	
	/**
	 * Return this canvas's mode.
	 * @return	This canvas's mode.
	 */
	public Mode getMode() {
		return mode;
	}
	
	/**
	 * Sets this canvas's mode to edit party mode.
	 */
	public void setEditPartyMode() {
		mode = Mode.EDITPARTY;
	}
	
	/**
	 * Sets this canvas's mode to edit message mode.
	 */
	public void setEditMessageMode() {
		mode = Mode.EDITMESSAGE;
	}
	
	/**
	 * Sets this canvas's mode to move party mode.
	 */
	public void setMovePartyMode() {
		mode = Mode.MOVEPARTY;
	}
	
	/**
	 * Sets this canvas's mode to add message mode.
	 */
	public void setAddMessageMode() {
		mode = Mode.ADDMESSAGE;
	}
	
	/**
	 * Sets this canvas's mode to default mode.
	 */
	public void setDefaultMode() {
		mode = Mode.DEFAULT;
	}
	
	/**
	 * An enumeration that indicates the view state of this canvas.
	 */
	public enum View{SEQUENCE, COMMUNICATION}
	
	/**
	 * Returns this canvas's view.
	 * @return		This canvas's view.
	 */
	public View getView() {
		return view;
	}
	
	/**
	 * Sets this canvas's view to a sequence diagram.
	 */
	public void setSequenceDiagram() {
		view = View.SEQUENCE;
	}
	
	/**
	 * Sets this canvas's view to a communication diagram.
	 */
	public void setCommunicationDiagram() {
		view = View.COMMUNICATION;
	}
	
	/**
	 * Returns this canvas's queue of result messages.
	 * @return		This canvas's queue of result messages.
	 */
	public ArrayList<ResultMessage> getResultQueue() {
		return resultQueue;
	}
	
	/**
	 * Adds a result message to this canvas's queue of result messages
	 * @param m		The result message to be added.
	 */
	public void addMessageResultQueue(ResultMessage m) {
		resultQueue.add(m);
	}
	
	/**
	 * Removes a result message from this canvas's queue of result messages
	 * @param m		The result message to be removed.
	 */
	public void deleteMessageResultQueue(ResultMessage m) {
		resultQueue.remove(m);
	}
	
	/**
	 * Searches for a result message in this canvas's queue with the given sender and receiver.
	 * @param s		The given sender.
	 * @param r		The given receiver.
	 * @return		The equivalent result message if it exists. Null otherwise.
	 */
	public ResultMessage searchResultQueue(Party s, Party r){
		
		ResultMessage result = null;
		
		for (ResultMessage m : resultQueue){
			if(s == m.getSentBy() && r == m.getReicevedBy()) {result = m;}
		}
		
		return result;
	}
	
	/**
	 * Searches for an invocation message in this canvas with the given sender and receiver.
	 * @param s		The given sender.
	 * @param r		The given receiver.
	 * @return		The equivalent invocation message if it exists. Null otherwise.
	 */
	public InvocationMessage findInvocationMessage(Party s, Party r) {
		for(Message m : messages) {
			if(s == m.getSentBy() && r == m.getReicevedBy() && m.getClass()==InvocationMessage.class) {
				return (InvocationMessage) m;
			}
		}
		return null;
	}
	
	/**
	 * Returns the collection of all the parties in the canvas.
	 * 
	 * @return		This canvas's parties.
	 */
	public HashSet<Party> getParties() {
		return parties;
	}
	
	/**
	 *  Sets the collection of parties of the canvas to a given collection.
	 * 
	 * @param parties	The given collection of parties.
	 */
	public void setParties(HashSet<Party> parties) {
		this.parties = parties;
	}

	/**
	 * 
	 * Returns the collection of all messages in the canvas.
	 * 
	 * @return		This canvas's parties.
	 */
	public HashSet<Message> getMessages() {
		return messages;
	}
	
	/**
	 *  Sets the collection of messages of the canvas to a given collection.
	 * 
	 * @param parties	The given collection of messages.
	 */
	public void setMessages(HashSet<Message> messages) {
		this.messages = messages;
	}
	
	/**
	 * Returns this canvas's width.
	 * 
	 * @return		This canvas's width.
	 */
	public int getWidth() {
		return this.width;
	}
	
	/**
	 * Returns this canvas's height.
	 * 
	 * @return		This canvas's height.
	 */
	public int getHeight() {
		return this.height;
	}
	
	/**
	 * 
	 * Adds a party to the canvas.
	 * 
	 * @param partyToAdd		The party to add.
	 */
	public void addParty(Party partyToAdd) {
		if(partyToAdd != null){
			this.parties.add(partyToAdd);
		}
	}
	
	/** 
	 * Adds a message to the canvas.
	 * 
	 * @param messageToAdd		The message to add.
	 */
	public void addMessage(Message messageToAdd){
		if(messageToAdd != null){
			this.messages.add(messageToAdd);
		}
	}
	
	/**
	 * 
	 * Deletes a party from the canvas
	 * 
	 * @param delParty		The party to delete.
	 */
	public void deleteParty(Party delParty){
		this.parties.remove(delParty);
	}
	
	/**
	 * 
	 * Deletes a message from the canvas.
	 * 
	 * @param delMessage		The message to delete.
	 */
	public void deleteMessage(Message delMessage){
		this.messages.remove(delMessage);
	}
	
	/**
	 * Switch this canvas's diagram type.
	 */
	public void switchView() {
		if(getView() == View.SEQUENCE) {setCommunicationDiagram();}
		else if(getView() == View.COMMUNICATION) {setSequenceDiagram();}	
	}
	
	/**
	 * 
	 * Makes a copy of all the messages in the canvas.
	 * 
	 * @return		A copy of all the messages in the canvas.
	 */
	public HashSet<Message> copyMessages(){
		HashSet<Message> copy = new HashSet<Message>();	
		for(Message m : messages) {
			copy.add(m);
		}
		return copy;
	}
	
	/**
	 * Updates the position of the labels after for example deletion of a message.
	 */
	public void updateLabels(){
		for(Message m : getMessages()) {
			if(m.getClass()==InvocationMessage.class) {
				int invocLabelX = Math.max(m.getReicevedBy().getPosSeq().getX(), m.getSentBy().getPosSeq().getX()) - Math.abs( (m.getReicevedBy().getPosSeq().getX() - m.getSentBy().getPosSeq().getX() )/2);
				int invocLabelY = this.origineY + this.getHeight()/6 + 42 + (50 * AddMessageHandler.getAmountPredecessors(this, m));
				m.getLabel().setLabelPositionSeq(invocLabelX, invocLabelY);
			}
		}
	}
	
	/**
	 * Updates all the positions in the communication diagram view of this canvas.
	 */
	public void updatePosComm() {
		messagesUpdate();
	}
	
	private void messagesUpdate(){
		LinkedList<LinkedList<Message>> listStacks = makeStackMessages();
		for( LinkedList<Message> list : listStacks){
			updatePosStackMessages(list);
		}
	}
	
	private LinkedList<LinkedList<Message>> makeStackMessages(){
		
		HashSet<Message> messageRemaining = new HashSet<Message>(this.messages);
		LinkedList<LinkedList<Message>> listStacks = new LinkedList<LinkedList<Message>>();
		for(Message m1 :this.messages ){
			if( messageRemaining.contains(m1)){
				LinkedList<Message> l1 = new LinkedList<Message>();
				l1.add(m1);
				messageRemaining.remove(m1);
				LinkedList<Message> lisToDelete = new LinkedList<Message>();
				for( Message m2 :  messageRemaining){
					if((m2.getReicevedBy() == m1.getReicevedBy() && m2.getSentBy() == m1.getSentBy()) || (m2.getReicevedBy() == m1.getSentBy() && m2.getSentBy()==m1.getReicevedBy())){
						l1.add(m2);
						lisToDelete.add(m2);
					}
				}
				for( Message mToDelete : lisToDelete){
					messageRemaining.remove(mToDelete);
				}
				listStacks.add(messageSort(l1));
			}
		}
		return listStacks;
	}
	
	private void updatePosStackMessages(LinkedList<Message> stackMessage) {

		Message m1 = stackMessage.get(0);
		int x1 = m1.getReicevedBy().getPosComm().getX();
		int x2 = m1.getSentBy().getPosComm().getX();
		int y1 = m1.getReicevedBy().getPosComm().getY();
		int y2 = m1.getSentBy().getPosComm().getY();
		
		int xLabel;
		int yLabel;
		
		// Length Vector
		double  length = Math.sqrt(  (Math.pow(Math.abs(x2-x1),2)) +  (Math.pow(Math.abs(y2-y1),2))  );
		int half = (int)(length/2);
		double A = Math.abs(x2-x1);
		double angle =  (Math.acos(A/length));
		// Place the label name(string) between the different parties
		if(x1<x2 && y1<y2 ) {
			xLabel = (int) (x1 +  half*Math.cos(angle));
			yLabel = (int) (y1 + half*Math.sin(angle));
		} else if (y2>y1 && x1>x2) {
			xLabel = (int) (x2 + half*Math.cos(angle));
			yLabel = (int) (y2 - half*Math.sin(angle));
		} else if (x1>x2 && y1>y2) {
			xLabel = (int) (x1 - half*Math.cos(angle));
			yLabel = (int) (y1 - half*Math.sin(angle));
		} else if (x1<x2 && y1>y2) {
			xLabel = (int) (x1 + half*Math.cos(angle));
			yLabel = (int) (y1 - half*Math.sin(angle));
		} else if (x1==x2 && y2>y1) {
			xLabel = x1;
			yLabel = y1 + half;
		} else if (x1==x2 && y2<y1) {
			xLabel = x1;
			yLabel = y1 - half;
		} else if (y1==y2 && x1<x2) {
			xLabel = x1 + half;
			yLabel = y1;
		} else {
			xLabel = x1 - half;
			yLabel = y1;
		}
		int yLabelUpdate = yLabel;
		for( Message m : stackMessage){
			m.getLabel().setLabelPositionComm(xLabel, yLabelUpdate);
			yLabelUpdate += 20;
		}
	}
	
	/**
	 * Sort a given collection of messages by their order in the system.
	 * 
	 * @param unsortedMessages		The collection of messages to sort.
	 * @return						The sorted list of messages.
	 */
	public static LinkedList<Message> messageSort(LinkedList<Message> unsortedMessages){
		LinkedList<Message> sorted = new LinkedList<Message>();
		int amount = unsortedMessages.size();
		int index = 0;
		int currentOrder = 1;
		while(index < amount) {
			Message lowest = getLowestOrderMessage(unsortedMessages, currentOrder);
			currentOrder = lowest.getOrder();
			unsortedMessages.remove(lowest);
			sorted.add(lowest);
			index++;
		}
		
		return sorted;
	}
	
	/**
	 * Returns the message from the given collection with the lowest order that is greater than or equal to the given index.
	 * 
	 * @param unsortedMessages		The given collection of messages.
	 * @param i						The given index.
	 * @return						The message with the lowest order greater than or equal to the given index.
	 */
	public static Message getLowestOrderMessage(LinkedList<Message> unsortedMessages, int i) {
		Message min = null;
		int minimum = Integer.MAX_VALUE;
		int order;
		for (Message m : unsortedMessages) {
			order = m.getOrder();
			if ((order >= i) && (order < minimum)) {
				min = m;
				minimum = order;
			}
		}
		return min;
	}
	
	/**
	 * Returns this canvas's the x coordinate of origin.
	 * @return		This canvas's x coordinate of origin.
	 */
	public int getOrigineX() {
		return origineX;
	}
	
	/**
	 * Sets this canvas's x coordinate of origin to the given x coordinate.
	 * @param origineX		The given x coordinate.
	 */
	public void setOrigineX(int origineX) {
		this.origineX = origineX;
	}
	
	/**
	 * Returns this canvas's the y coordinate of origin.
	 * @return		This canvas's y coordinate of origin.
	 */
	public int getOrigineY() {
		return origineY;
	}
	
	/**
	 * Sets this canvas's y coordinate of origin to the given y coordinate.
	 * @param origineY		The given y coordinate.
	 */
	public void setOrigineY(int origineY) {
		this.origineY = origineY;
	}
	
	/**
	 * Returns the interaction this canvas is a part of.
	 * @return		This canvas's interaction.
	 */
	public Interaction getInteraction() {
		return interaction;
	}
	
	/**
	 * Sets this canvas's interaction to the given interaction.
	 * @param i		The given interaction.
	 */
	public void setInteractioin(Interaction i) {
		this.interaction = i;
	}
	
	/**
	 * Returns this canvas's framework.
	 * @return		This canvas's framework.
	 */
	public frameWork getFramework() {
		return framework;
	}
	
	/**
	 * Sets this canvas's framework to the given framework.
	 * @param framework		The given framework.
	 */
	public void setFramework(frameWork framework) {
		this.framework = framework;
	}
	
	/**
	 * Sets this canvas's mode to the given mode.
	 * @param m		The given mode.
	 */
	public void setMode(Mode m) {
		this.mode = m;
	}
	
	/**
	 * Resizes the right side of this canvas.
	 * @param xMouse		The x coordinate to resize to.
	 */
	public void resizeXRightCanvas(int xMouse) {
		this.width = Math.max(this.getFramework().getBar().getMinimumWidth()+4, xMouse - this.getOrigineX());
	}
	
	/**
	 * Resizes the lower side of this canvas.
	 * @param yMouse		The y coordinate to resize to.
	 */
	public void resizeYLowerCanvas(int yMouse) {
		this.height =  Math.max(this.getFramework().getBar().getHeight()+4, yMouse - this.getOrigineY());
	}
	
	/**
	 * Resizes the left side of this canvas.
	 * @param xMouse		The x coordinate to resize to.
	 */
	public void resizeXLeftCanvas(int xMouse) {
		int oldOrigine = this.getOrigineX();
		int oldWidth = this.getWidth();
		if( xMouse <= oldOrigine) {
			this.width += Math.abs(oldOrigine-xMouse);
			this.origineX = oldOrigine - Math.abs(oldOrigine -xMouse);
		} else {
			this.width -= Math.abs(oldOrigine-xMouse);
			this.origineX = oldOrigine + Math.abs(oldOrigine -xMouse);
		}
		if( this.getFramework().getBar().getMinimumWidth()+4 >= this.width	) {
			this.origineX = oldOrigine;
			this. width = oldWidth;
		}
	}
	
	/**
	 * Resizes the upper side of this canvas.
	 * @param yMouse		The y coordinate to resize to.
	 */
	public void resizeYTopCanvas(int yMouse) {
		int oldOrigine = this.getOrigineY();
		int oldHeight = this.height;
		if( yMouse <= oldOrigine) {
			this.height += Math.abs(oldOrigine-yMouse);
			this.origineY = oldOrigine - Math.abs(oldOrigine -yMouse);
		} else {
			this.height -= Math.abs(oldOrigine-yMouse);
			this.origineY = oldOrigine + Math.abs(oldOrigine -yMouse);
		}
		if (this.getFramework().getBar().getHeight()+4 >= this.height ) {
			this.origineY = oldOrigine;
			this.height = oldHeight;
		}
	}
	
	/**
	 * Sets this canvas's width to the given width.
	 * @param width		The given width.
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	
	/**
	 * Sets this canvas's height to the given height.
	 * @param height		The given height.
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	
	/**
	 * Returns the first available number for parties in a given canvas.
	 * @param canvas		The given canvas.
	 * @return				The first available number for parties.
	 */
	public static int getAvailablePartyNumber(Canvas canvas) {
		LinkedList<Party> copyParties = new LinkedList<Party>();
		copyParties.addAll(canvas.getParties());		
		
		int newPartyNumber = 0;
		for(Party p : canvas.getParties() ) {
			if( newPartyNumber != p.getPartyNumber()) {
				// Search the rest of the party list for the same PartyNumber
				boolean foundEqualNumber = false;
				for (Party pCopy : copyParties) {
					if ( pCopy.getPartyNumber() == newPartyNumber) {
						foundEqualNumber = true;
					}
				}
				if(!foundEqualNumber) {
					break;
				}
			}
			
			newPartyNumber++;
		}
		return newPartyNumber;
	}
	
	/**
	 * Returns the first available number for messages in a given canvas.
	 * @param canvas		The given canvas.
	 * @return				The first available number for messags.
	 */
	public static int getAvailableMessageNumber(Canvas canvas) {
		LinkedList<Message> copyMessages = new LinkedList<Message>();
		if( !canvas.getMessages().isEmpty()) {
			copyMessages.addAll(canvas.getMessages());
		}
		int newPartyNumber = 0;
		for(Message p : canvas.getMessages() ) {
			if( newPartyNumber != p.getMessageNumber()) {
				// Search the rest of the party list for the same PartyNumber
				boolean foundEqualNumber = false;
				for (Message pCopy : copyMessages) {
					if ( pCopy.getMessageNumber() == newPartyNumber) {
						foundEqualNumber = true;
					}
				}
				if(!foundEqualNumber) {
					break;
				}
			}
			
			newPartyNumber++;
		}
		return newPartyNumber;
	}
	
	/**
	 * Returns the party in a given canvas that has the given party number.
	 * @param canvas		The given canvas.
	 * @param partyNumber	The given party number
	 * @return				The party with the given party number if it exists. Null otherwise.
	 */
	public static Party findPartyByNumber(Canvas canvas,int partyNumber) {
		Party returnParty =null;
		for( Party p: canvas.getParties()) {
			if(p.getPartyNumber() == partyNumber) {
				return p;
			}
		}
		return returnParty;
	}
	
	/**
	 * Returns the message in a given canvas that has the given party number.
	 * @param canvas		The given canvas.
	 * @param partyNumber	The given party number.
	 * @return				The message with the given party number if it exists. Null otherwise.
	 */
	public static Message findMessageByNumber(Canvas canvas,int partyNumber) {
		Message returnMessage =null;
		for( Message p: canvas.getMessages()) {
			if(p.getMessageNumber() == partyNumber) {
				return p;
			}
		}
		return returnMessage;
	}
	
	/**
	 * Deletes all this canvas's messages.
	 */
	public void deleteAllMessages() {
		this.messages = new HashSet<Message>();
	}
	
}