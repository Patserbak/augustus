package Model.Handler;
import Controller.Mouse;
import Model.Button;
import Model.Canvas;
import Model.Label;
import Model.Message;
import Model.Mode;
import Model.Party;
import Model.PartyRole;
import Model.ResultMessage;
import Model.Window;
import Model.titleBar;

/**
 * A handler that handles the actions of an element being selected.
 */
public class SelectElementHandler extends Handler {
	
	/*
	 * Assuming label coordinates refer to upper left corner
	 */
	
	/*
	 * id = 0 : Mouse Clicked 
	 * -> If mouse coordinates in label
	 * 		Then select label
	 * 	  Else do nothing
	 * 
	 * 
	 * id = 1 : Mouse Pressed
	 * -> If mouse coordinates in party
	 * 		Then select party
	 * 	  Else If x is on party lifeline
	 * 		Then select lifeline
	 * 	  Else do nothing
	 * 
	 * id = 2 : Mouse Released
	 * -> If party is selected
	 * 		Then deselect
	 *    Else do nothing
	 *
	 */
	
	/**
	 * Handles an element being selected.
	 * @param canvas		The canvas to edit.
	 * @param x				The x coordinate of the mouse event used to handle these events.
	 * @param y				The y coordinate of the mouse event used to handle these events.
	 * @param id			The kind of mouse event.
	 */
	public static void handle(Canvas canvas, int x, int y, Mouse id) {
		
		if(id==Mouse.SINGLECLICK) {
			
			// LET OP!
			deselectAll(canvas);
			
			Label l = getLabelAt(x, y, canvas);
			Party p = getPartyAt(x, y, canvas);
			
			// Closing Canvas 
			if (closeCanvas(canvas,x,y)) {
				canvas.setMode(Mode.CLOSING);
			}
			
			if(p == null && l == null) {deselectAll(canvas);}
			
			if(l!=null) {l.setSelected(true);}
			if(p!=null) {p.setSelected(true);}
		}
		
		else if(id == Mouse.PRESSED) {
			
			if(existsSender(canvas)) {return;}
			Party p = getPartyAt(x, y, canvas); if(p==null) {System.out.println("NUll_1");}
			Party lifeLine = approxLifeLine(x, canvas); if(lifeLine==null) {System.out.println("NULL_2");}
			
			int oldXorigine = canvas.getOrigineX();
			int oldYorigine = canvas.getOrigineY();
			int newXorigine = (x-(canvas.getFramework().getBar().getWidth(canvas)/2));
			int newYorigine = (y-(canvas.getFramework().getBar().getHeight()/2));
			
			
			if( moveCanvas(canvas, x,y)) {
				MoveWindowHandler.handle(canvas ,oldXorigine,oldYorigine, newXorigine,newYorigine);
			}
			else if( resizeCornerCanvas(canvas, x, y)) {
				ResizeWindowHandler.handle(canvas, x, y, Window.ResizeCorner);
			}
			else if( resizeXCanvas(canvas,x) ) {
				ResizeWindowHandler.handle(canvas, x, y, Window.ResizeX);
			}
			else if( resizeYCanvas(canvas,y)) {
				ResizeWindowHandler.handle(canvas, x, y, Window.ResizeY);
			}
			else if(p  != null) {
				canvas.setMovePartyMode();
				p.setSelected(true);
				System.out.println("MovePartyMode");
				System.out.println("PARTY SELECTED: "+p.getSelected());}
			else if(lifeLine != null){
				canvas.setAddMessageMode();
				lifeLine.setSelected(true);
				lifeLine.makeSender();
				lifeLine.setSelectedYPosition(y);
				System.out.println("AddMessageMode");
				System.out.println("LifeLine SELECTED: "+lifeLine.getSelected());}
			}
		else if ( id == Mouse.RELEASED && canvas.getMode() == Mode.ADDMESSAGE){
			resetRoles(canvas);
			canvas.setDefaultMode();
			System.out.println("######## Releasing Button + Editing Label Message ########");
		}
		else if(id == Mouse.RELEASED && canvas.getMode()!=Mode.DEFAULT) {
			deselectAll(canvas);
			resetRoles(canvas);
			canvas.setDefaultMode();
			System.out.println("######## Releasing Button ########");
		}
	}

	private static void deselectAll(Canvas canvas) {
		deselectParties(canvas);
		deselectMessages(canvas);
	}
	
	private static void deselectParties(Canvas canvas) {
		for(Party p : canvas.getParties()) {
				p.setSelected(false);
				p.getLabel().setSelected(false);
		}
	}

	private static void deselectMessages(Canvas canvas) {
		for(Message m : canvas.getMessages()) {
			m.setSelected(false);
			if(m.getClass()!=ResultMessage.class) {	m.getLabel().setSelected(false);}
		}
	}
	
	private static boolean existsSender(Canvas canvas) {
		
		for(Party p : canvas.getParties()) {
			if(p.getRole()==PartyRole.SENDER) {return true;}
		}
		
		return false;
	}
	
	private static boolean closeCanvas(Canvas canvas, int xMouse, int yMouse) {
		Button button = canvas.getFramework().getBar().getButton();
		int buttonOrigineX = button.getOrigineX();
		int buttonOrigineY = button.getOrigineY();
		int upperX = buttonOrigineX + button.getWidth();
		int upperY = buttonOrigineY + button.getHeight();
		if( xMouse >= buttonOrigineX && xMouse <= upperX && yMouse >= buttonOrigineY && yMouse <= upperY ) {
			return true;
		}
		return false;
	}
	
	private static boolean moveCanvas(Canvas canvas,int xMouse, int yMouse) {
		// If cursor is in button Area => wait for id==Mouse.SINGLECLICK in handle Method
		if(closeCanvas(canvas, xMouse,yMouse)) {
			return false;
		}
		
		titleBar bar = canvas.getFramework().getBar();
		int barOrigineX = bar.getOrigineX();
		int barOrigineY = bar.getOrigineY();
		int upperX = barOrigineX + canvas.getFramework().getBar().getWidth(canvas);
		int upperY = barOrigineY + canvas.getFramework().getBar().getHeight();
		if( xMouse >= barOrigineX && xMouse <= upperX && yMouse >= barOrigineY && yMouse <= upperY ) {
			return true;
		}
		return false;
	}
	
	private static boolean resizeXCanvas(Canvas canvas, int xMouse ) {
		int xLow = canvas.getOrigineX() + canvas.getWidth() - 4;
		int xHigh = xLow + 8;
		if ( xMouse >= xLow && xMouse <= xHigh) {
			return true;
		}
		return false;
	}
	
	private static boolean resizeYCanvas(Canvas canvas, int yMouse) {
		int yLow = canvas.getOrigineY() + canvas.getHeight() -4;
		int yHigh = yLow + 8;
		if(yMouse  >= yLow && yMouse <= yHigh) {
			return true;
		}
		return false;
	}
	
	private static boolean resizeCornerCanvas(Canvas canvas, int xMouse, int yMouse) {
		int xLow = canvas.getOrigineX() + canvas.getWidth() - 4;
		int xHigh = xLow + 8;
		int yLow = canvas.getOrigineY() + canvas.getHeight() -4;
		int yHigh = yLow + 8;
		if ( xMouse >= xLow && xMouse <= xHigh && yMouse  >= yLow && yMouse <= yHigh) {
			return true;
		}
		return false;
	}

}
