package Model.Handler;

import Model.Canvas;
import Model.Message;
import Model.Party;
import Model.Window;

/**
 * A handler that handles the actions of a window being resized.
 */

public class ResizeWindowHandler extends Handler{

	/**
	 * Handles the resizing of a window
	 * 
	 * @param canvas	The canvas being resized.
	 * @param x			New X coordinate
	 * @param y			New Y coordinate
	 * @param action	Resize action
	 */
	
	public static void handle(Canvas canvas, int x, int y, Window action) {
		switch(action) {
		case ResizeX:
			canvas.resizeXCanvas(x);
			break;
		case ResizeY:
			canvas.resizeYCanvas(y);
			updateYPositionPartySequenceDiagram(canvas);
			updateYPositionLMessageLabelsSequenceDiagram(canvas);
			break;
		case ResizeCorner:
			canvas.resizeCornerCanvas(x,y);
			updateYPositionLMessageLabelsSequenceDiagram(canvas);
			updateYPositionPartySequenceDiagram(canvas);
			break;
		}
	}
	
	/**
	 * Updates the position of Messages of the given canvas.
	 * @param canvas	Canvas for which to update the message positions
	 * 
	 */
	
	//TODO Move to canvas?
	
	public static void updateYPositionLMessageLabelsSequenceDiagram(Canvas canvas) {
		for (Message m : canvas.getMessages()) {
			int newY = canvas.getOrigineY() +canvas.getHeight()/6 + 42 + (50 * AddMessageHandler.getAmountPredecessors(canvas, m));
			m.getLabel().setLabelPositionSeq(m.getLabel().getLabelPositionSequence().getX(), newY);
		}
	}
	
	/**
	 * Updates the position of Parties of the given canvas.
	 * @param canvas	Canvas for which to update the Party positions
	 * 
	 */
	
	//TODO Move to canvas?
	
	private static void updateYPositionPartySequenceDiagram(Canvas canvas) {
		for( Party p : canvas.getParties()) {
			int newY = canvas.getOrigineY() +canvas.getHeight()/12;
			p.setPosSeq(p.getPosSeq().getX(), newY);
			p.getLabel().setLabelPositionSeq(p.getLabel().getLabelPositionSequence().getX(),newY +p.getHeight() + 10);
		}
	}
	
}
