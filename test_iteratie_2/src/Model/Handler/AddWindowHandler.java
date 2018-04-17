package Model.Handler;

import java.util.Random;
import java.util.Stack;

import Model.Canvas;
import Model.Interaction;
import Model.Party;

/**
 * A handler that handles the actions of a window being added.
 */

public class AddWindowHandler extends Handler{
	
	private static Random randNumberPos = new Random();
	
	/**
	 * Pushes a cloned version of the top window of the provided stack onto that stack.
	 * 
	 * @param subWindows 	Provided Stack of Windows
	 */
	
	public static void handle(Stack<Canvas> subWindows) {

		// Add new Subwindow to current Interaction
		Interaction i = subWindows.lastElement().getInteraction();
		int xOrigineRandom = randNumberPos.nextInt(250);
		int yOrigineRandom = randNumberPos.nextInt(250);
		Canvas c = new Canvas(subWindows.lastElement().getWidth(),subWindows.lastElement().getHeight(),xOrigineRandom,yOrigineRandom,i);
		
		// Clone Parties 
		for ( Party p : subWindows.lastElement().getParties()) {
			Party partyToAdd = (Party) p.clone();
			c.addParty(partyToAdd);
		}
		
		int oldXorigine = subWindows.lastElement().getOrigineX();
		int oldYorigine = subWindows.lastElement().getOrigineY();
		int newXorigine = c.getOrigineX();
		int newYorigine = c.getOrigineY();
		MoveWindowHandler.updatePartyPositions(c , oldXorigine, oldYorigine, newXorigine, newYorigine);
		
		Interaction.copyMessages(subWindows.lastElement(), c);
		
		/**
		// Clone Messages
		for( Message m : subWindows.lastElement().getMessages()) {
			Message messageToAdd = (Message) m.clone();
			c.addMessage(messageToAdd);
		}
		SelectElementHandler.updateMessagePositions(c , oldXorigine, oldYorigine, newXorigine, newYorigine);
		for( Message m : c.getMessages()) {
			Interaction.updateMessagePropertiesAfterClone(subWindows.lastElement(),c, m);
		}
		*/
		
		i.addCanvas(c);
		subWindows.push(c);
	}

}
