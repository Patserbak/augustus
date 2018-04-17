package Model.Handler;

import java.util.ArrayList;
import java.util.Stack;

import Model.Canvas;
import Model.Mode;

/**
 * A handler that handles the closing of a window.
 */

public class CloseWindowHandler extends Handler{

	/**
	 * Closes marked windows.
	 * 
	 * @param subWindows	List from which to close marked windows.
	 */
	
	public static void handle(ArrayList<Canvas> subWindows) {
		// Find any canvas objects that need to be closed/deleted!
		ArrayList<Canvas> toBeDeleted = new ArrayList<Canvas>();
		for( Canvas c :subWindows) {
			if(c.getMode()  == Mode.CLOSING) {
				toBeDeleted.add(c);
			}
		}
		for (Canvas c : toBeDeleted) {
			subWindows.remove(c);
		}
	}

	/**
	 * Closes marked windows.
	 * 
	 * @param subWindows	Stack from which to remove marked windows.
	 */
	
	public static void handle(Stack<Canvas> subWindows) {
		// Find any canvas objects that need to be closed/deleted!
		ArrayList<Canvas> toBeDeleted = new ArrayList<Canvas>();
		for( Canvas c :subWindows) {
			if(c.getMode()  == Mode.CLOSING) {
				toBeDeleted.add(c);
			}
		}
		for (Canvas c : toBeDeleted) {
			subWindows.remove(c);
		}
	}

}
