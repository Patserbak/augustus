package Model.Handler;

import Model.Canvas;

/**
 * A handler that handles the actions of changing the type of visual representation of the canvas.
 * From sequence diagram to communication diagram and vice versa.
 */
public class SwitchViewHandler extends Handler{
	
	/**
	 * Handles the change of visual representation type.
	 * @param canvas		The canvas to edit.
	 */
	
	public static void handle(Canvas canvas) {
		canvas.switchView();
	}
}
