package Model;
import java.util.ArrayList;
import java.util.Random;
import Model.Handler.MoveWindowHandler;
import Model.Handler.ResizeWindowHandler;

public class Interaction {
	
	public Interaction(int i) {
		subWindows = new ArrayList<Canvas>();
		//int xOrigineRandom = randNumberPos.nextInt(250);
		//int yOrigineRandom = randNumberPos.nextInt(250);
		
		Canvas c = new Canvas(300, 300, i, i,this );
		subWindows.add(c);
	}

	private ArrayList<Canvas> subWindows = new ArrayList<Canvas>();
	//private Random randNumberPos = new Random();

	public ArrayList<Canvas> getSubWindows(){
		return subWindows;
	}
	
	public void deleteCanvas(Canvas c) {
		subWindows.remove(c);
	}
	public void addCanvas(Canvas c) {
		c.setInteractioin(this);
		subWindows.add(c);
	}
	public void adjusted(ADJUSTED_TYPE type, Canvas updatedCanvas) {
		// dececide what is adjusted
		switch(type) {
		
		case ADDED_PARTY: 
			updateParties(updatedCanvas);
			break;
		case PARTY_LABEL: 
			updatePartyLabels(updatedCanvas);
			break;
		case ADDED_MESSAGE: 
			updateMessages(updatedCanvas);
			break;
		case MESSAGE_LABEL: 
			updateMessageLabels(updatedCanvas);
			break;
		case CHANGE_TYPE:
			updatePartyTypes(updatedCanvas);
			break;
		case DELETE_MESSAGE:
			updateDeletePartyOrMessage(updatedCanvas);
		}	
	}
	private void updateParties(Canvas updatedCanvas) {
		if( subWindows.size() > 1 ) {
			Canvas usedForFindingPartyNumber=null;
			for(Canvas c : subWindows) {
				if( !c.equals(updatedCanvas)) {
					usedForFindingPartyNumber = c;
				}
			}
			// this found partyNumber is the partyNumber of the latest added party in updatedCanvas
			int partyNumber = Canvas.getAvailablePartyNumber(usedForFindingPartyNumber);
			Party partyJustAdded= Canvas.findPartyByNumber(updatedCanvas, partyNumber);
			// Add Party to every Canvas in the Interaction!
			for(Canvas c : subWindows) {
				Party partyToAdd = (Party) partyJustAdded.clone();
				if( !c.equals(updatedCanvas)) {
					
					c.addParty(partyToAdd);
					
					// See SelectElementHandler.updatePartyPositions (Same principle)
					
					int oldXorigine = updatedCanvas.getOrigineX();
					int oldYorigine = updatedCanvas.getOrigineY();
					int newXorigine = c.getOrigineX();
					int newYorigine = c.getOrigineY();
					
					// Update Positions Sequence Diagram
					int xSeq = partyToAdd.getPosSeq().getX();
					int ySeq = partyToAdd.getPosSeq().getY();
					
					int dx = Math.abs((xSeq-oldXorigine));
					int dy = Math.abs((ySeq-oldYorigine));
					
					partyToAdd.setPosSeq((newXorigine+dx),(newYorigine + dy));
					// Update Label Position for  Sequence Diagram
					int yLabel = newYorigine + c.getHeight()/12 + +partyToAdd.getHeight() + 10; // Needs to be in sync with AddPartyHandler
					partyToAdd.getLabel().setLabelPositionSeq((newXorigine+dx), yLabel);
					
					// Update Positions Communication Diagram
					int xCom = partyToAdd.getPosComm().getX();
					int yCom = partyToAdd.getPosComm().getY();
					
					dx = Math.abs((xCom-oldXorigine));
					dy = Math.abs((yCom-oldYorigine));
					
					partyToAdd.setPosComm((newXorigine + dx), (newYorigine+dy));
					// Update Label Postion for Communication Diagram
					// Label Postion in communication view is not used......... (See Add PartyHandler)
					
				}
			}
		}
	}
	private void updatePartyLabels(Canvas updatedCanvas) {
		for(Canvas c : subWindows) {
			for( Party p : c.getParties()) {
				// Get updated Label name from "updatedCanvas"
				String newLabelName = Canvas.findPartyByNumber(updatedCanvas, p.getPartyNumber()).getLabel().getLabelname();
				p.getLabel().setLabelname(newLabelName);
				p.getLabel().setLabelname(newLabelName);
				String name = p.getLabel().getLabelname();
				p.getLabel().setWidth(8*name.length());
			}
		}
	}
	private void updateMessages(Canvas updatedCanvas) {
		if( subWindows.size() > 1 ) {
			
			for( Canvas c : subWindows) {
				if( !c.equals(updatedCanvas)) {
					c.deleteAllMessages();
					copyMessages(updatedCanvas, c);
				}
			}
			/**
			Canvas usedForFindingMessageNumber=null;
			for(Canvas c : subWindows) {
				if( !c.equals(updatedCanvas)) {
					usedForFindingMessageNumber = c;
				}
			}
			int messageNumber = Canvas.getAvailableMessageNumber(usedForFindingMessageNumber);
			Message messageJustAdded= Canvas.findMessageByNumber(updatedCanvas, messageNumber);
			// Add Message to every Canvas in the Interaction!
			for(Canvas c : subWindows) {
				Message messageToAdd = (Message) messageJustAdded.clone();
				if( !c.equals(updatedCanvas)) {
					c.addMessage(messageToAdd);
					
					// See SelectElementHandler.updateMessagePositions (Same principle)
	
					int oldXorigine = updatedCanvas.getOrigineX();
					int oldYorigine = updatedCanvas.getOrigineY();
					int newXorigine = c.getOrigineX();
					int newYorigine = c.getOrigineY();
					
					// Update Label Positions Sequence Diagram
					int xSeq = messageToAdd.getLabel().getLabelPositionSequence().getX();
					int ySeq = messageToAdd.getLabel().getLabelPositionSequence().getY();
					
					int dx = Math.abs((xSeq-oldXorigine));
					int dy = Math.abs((ySeq-oldYorigine));
					
					messageToAdd.getLabel().setLabelPositionSeq((newXorigine+dx),(newYorigine + dy));
					// Update Label Positions Communication Diagram
					int xCom = messageToAdd.getLabel().getLabelPositionComm().getX();
					int yCom = messageToAdd.getLabel().getLabelPositionComm().getY();
					
					dx = Math.abs((xCom-oldXorigine));
					dy = Math.abs((yCom-oldYorigine));
					
					messageToAdd.getLabel().setLabelPositionComm((newXorigine+dx), (newYorigine+dy));
					
					updateMessagePropertiesAfterClone(updatedCanvas,c, messageToAdd);
				}
			}*/
			
		}
	}
	private void updateMessageLabels(Canvas updatedCanvas) {
		for(Canvas c : subWindows) {
			for( Message p : c.getMessages()) {
				// Get updated Label name from "updatedCanvas"
				String newLabelName = Canvas.findMessageByNumber(updatedCanvas, p.getMessageNumber()).getLabel().getLabelname();
				p.getLabel().setLabelname(newLabelName);
				String name = p.getLabel().getLabelname();
				p.getLabel().setWidth(8*name.length());
			}
		}
	}
	public static void updateMessagePropertiesAfterClone(Canvas updatedCanvas , Canvas newCanvas, Message messageToAdd ) {
		// Update Message properties to new Canvas after clone!
		int numberSentBy = Canvas.findPartyByNumber(updatedCanvas, messageToAdd.getSentBy().getPartyNumber()).getPartyNumber();
		messageToAdd.setSentBy(Canvas.findPartyByNumber(newCanvas, numberSentBy));
		int numberReceivedBy = Canvas.findPartyByNumber(updatedCanvas, messageToAdd.getReicevedBy().getPartyNumber()).getPartyNumber();
		messageToAdd.setReicevedBy(Canvas.findPartyByNumber(newCanvas, numberReceivedBy));
		if (messageToAdd.getPredecessor() != null) {
			int numberPredecessor = Canvas.findMessageByNumber(updatedCanvas, messageToAdd.getPredecessor().getMessageNumber()).getMessageNumber();
			messageToAdd.setPredecessor(Canvas.findMessageByNumber(newCanvas,numberPredecessor));
		}
		if (messageToAdd.getResult() != null) { // Invocation Message has Result == null
			int numberResult = Canvas.findMessageByNumber(updatedCanvas, messageToAdd.getResult().getMessageNumber()).getMessageNumber();
			messageToAdd.setResult((ResultMessage)Canvas.findMessageByNumber(newCanvas, numberResult));
		}
	}
	
	private void updatePartyTypes(Canvas updatedCanvas){
		for(Canvas c : subWindows) {
			if( !c.equals(updatedCanvas)) { // No concurrent modification !
				for( Party p : updatedCanvas.getParties()) {
					
					// Clone Party with correct type -> remove Old Party -> Add new Party with correct type
	
					Party partyToFindInUpdatedCanvas = Canvas.findPartyByNumber(updatedCanvas, p.getPartyNumber());
					
					Party partyToAdd = partyToFindInUpdatedCanvas.clone();
					
					Party partyToDelete = Canvas.findPartyByNumber(c, p.getPartyNumber());
					
					// Update Message with new Party (partyToAdd)
					for( Message m : c.getMessages()) {
						if( m.getSentBy().equals(partyToDelete)) {
							m.setSentBy(partyToAdd);
						}
						if( m.getReicevedBy().equals(partyToDelete)) {
							m.setReicevedBy(partyToAdd);
						}
					}
										
					c.deleteParty(partyToDelete);		
					c.addParty(partyToAdd);
					
				
					
					partyToAdd.setPosSeq(partyToDelete.getPosSeq().getX(),partyToDelete.getPosSeq().getY());
					partyToAdd.getLabel().setLabelPositionSeq(partyToDelete.getLabel().getLabelPositionSequence().getX(), partyToDelete.getLabel().getLabelPositionSequence().getY());
					
					partyToAdd.setPosComm(partyToDelete.getPosComm().getX(), partyToDelete.getPosComm().getY());
					partyToAdd.getLabel().setLabelPositionComm(partyToDelete.getLabel().getLabelPositionComm().getX(), partyToDelete.getLabel().getLabelPositionComm().getY());
					// Update Label Postion for Communication Diagram
					// Label Postion in communication view is not used......... (See Add PartyHandler)
					
				}
			}
		}
	}
	private void updateDeletePartyOrMessage(Canvas updatedCanvas) {
		updateMessages(updatedCanvas);
		
		if( subWindows.size() > 1 ) {
			Canvas usedForFindingPartyNumber=null;
			for(Canvas c : subWindows) {
				if( !c.equals(updatedCanvas)) {
					usedForFindingPartyNumber = c;
				}
			}
			ArrayList<Party> listToDelete = new ArrayList<Party>();
			for( Party p :usedForFindingPartyNumber.getParties()) {
				if( Canvas.findPartyByNumber(updatedCanvas, p.getPartyNumber()) == null){
					listToDelete.add(p);
				}
			}
			for( Canvas  c: subWindows) {
				if( !c.equals(updatedCanvas)) {
					for (Party partyToDelte :  listToDelete) {
						c.getParties().remove(Canvas.findPartyByNumber(c, partyToDelte.getPartyNumber()));
					}
				}
			}
		}
		/**
		if( subWindows.size() > 1 ) {
			for( Canvas c : subWindows) {
				if( !c.equals(updatedCanvas)) {
					c.deleteAllMessages();
					
				
					int oldXorigine = updatedCanvas.getOrigineX();
					int oldYorigine = updatedCanvas.getOrigineY();
					int newXorigine = c.getOrigineX();
					int newYorigine = c.getOrigineY();
					
					// Clone Messages
					for( Message m : updatedCanvas.getMessages()) {
						Message messageToAdd = (Message) m.clone();
						c.addMessage(messageToAdd);
					}
					SelectElementHandler.updateMessagePositions(c , oldXorigine, oldYorigine, newXorigine, newYorigine);
					for( Message m : c.getMessages()) {
						Interaction.updateMessagePropertiesAfterClone(updatedCanvas,c, m);
					}
					
				}
			}
		}
		*/
	}
	public static void copyMessages(Canvas updatedCanvas , Canvas notUpdatedCanvas) {
		
		int oldXorigine = updatedCanvas.getOrigineX();
		int oldYorigine = updatedCanvas.getOrigineY();
		int newXorigine = notUpdatedCanvas.getOrigineX();
		int newYorigine = notUpdatedCanvas.getOrigineY();
		
		// Clone Messages
		for( Message m : updatedCanvas.getMessages()) {
			Message messageToAdd = (Message) m.clone();
			notUpdatedCanvas.addMessage(messageToAdd);
		}
		MoveWindowHandler.updateMessagePositions(notUpdatedCanvas , oldXorigine, oldYorigine, newXorigine, newYorigine);
		for( Message m : notUpdatedCanvas.getMessages()) {
			Interaction.updateMessagePropertiesAfterClone(updatedCanvas,notUpdatedCanvas, m);
		}
		ResizeWindowHandler.updateYPositionLMessageLabelsSequenceDiagram(notUpdatedCanvas);
	}
}


