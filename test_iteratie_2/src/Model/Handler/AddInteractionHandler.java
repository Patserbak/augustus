package Model.Handler;

import java.util.ArrayList;
import java.util.Stack;

import Model.Canvas;
import Model.Interaction;

/**
 * A handler that handles the actions of an interaction being added.
 */

public class AddInteractionHandler extends Handler{

	/**
	 * Handles an interaction being added
	 * 
	 * @param interactions	List to which to add the new interaction.
	 * 
	 * @param subWindows	Stack on top of which the window of the new interaction should be pushed.
	 */
	
	public static void handle(ArrayList<Interaction> interactions, Stack<Canvas> subWindows) {
		// New interaction
		Interaction i = new Interaction(subWindows.size()*10);
		interactions.add(i);
		for( Canvas c : i.getSubWindows()) {
			subWindows.push(c);
		}
	}

}
