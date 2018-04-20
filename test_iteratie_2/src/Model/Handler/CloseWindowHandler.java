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
	 * Closes all subwindows that need to be closed from the given list of subwindows.
	 * 
	 * @param subWindows	The given list of subwindows.
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
	 * Closes all subwindows that need to be closed from the given stack of subwindows.
	 * 
	 * @param subWindows	The given stack of subwindows.
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
