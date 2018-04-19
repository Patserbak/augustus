package Controller;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Stack;

import Model.Canvas;
import Model.Interaction;
import Model.Screen;
import Model.Handler.AddInteractionHandler;
import Model.Handler.AddWindowHandler;
import Model.Handler.CloseWindowHandler;
import Model.Handler.EditLabelHandler;

public class MyScreen {

	/////////////////// Singleton ///////////////////
	private static MyScreen instance = null;
	
	private MyScreen() {}
	
	private synchronized static void createInstance() {
	if(instance == null) {instance = new MyScreen();}
	}
	
	public static MyScreen getInstance() {
	if(instance==null) {createInstance();}
	return instance;
	}
	
	////////////////////////////////////////////////
	
	private boolean ctrlPressed =  false;
	
	public void mouseClicked(Mouse id, int x, int y, Screen screen) {
		// First check if a party label is left in a valid state + check if there are any interactions
		if (!screen.getInteractions().isEmpty() && !EditLabelHandler.editLabelModeParty(screen.getSubWindows().lastElement())) {
			// Determine Canvas 
			Canvas canvas = null;
			
			
			Stack<Canvas> findList = new Stack<Canvas>();
			findList.addAll(screen.getSubWindows());
			boolean found = false;
			Canvas top = findList.lastElement();
			while(!findList.isEmpty() && !found) {
				canvas = findList.pop();
				if( isInArea(x, y, canvas)) {
					found = true;
				}
			}
			// Selected SubWindow must be placed on top of the stack of subWindows
			screen.getSubWindows().remove(canvas);
			screen.getSubWindows().push(canvas);
			
			// Check if the selected canvas was on top of the stack(active canvas)
			if( top == canvas) {
				// Delegate to Interaction
				MyInteraction.mouseClicked(id, x, y, canvas,canvas.getInteraction() );
			}
		}
		
		CloseWindowHandler.handle(screen.getSubWindows());
		
		// Find any Empty Interaction(empty interaction =  canvas left) ==> delete empty Interaction
		ArrayList<Interaction> toBeDeletedInteraction = new ArrayList<Interaction>();
		for( Interaction i :screen.getInteractions()) {
			if(i.getSubWindows().isEmpty()) {
				toBeDeletedInteraction.add(i);
			}
		}
		for (Interaction i : toBeDeletedInteraction) {
			screen.getInteractions().remove(i);
		}
	}
	
	public void keyPressed(int id, int keyCode, char keyChar, Screen screen) {

		if( ctrlPressed && keyCode == 78 && (id == KeyEvent.KEY_PRESSED || id == KeyEvent.KEY_TYPED)) {
			AddInteractionHandler.handle(screen.getInteractions(), screen.getSubWindows());
		
		}else if ( !screen.getInteractions().isEmpty() && ctrlPressed && keyCode == 68 && (id == KeyEvent.KEY_PRESSED || id == KeyEvent.KEY_TYPED) ) {
			AddWindowHandler.handle(screen.getSubWindows());
			
		} else {
			ctrlPressed = false;
		}		
		if( id == KeyEvent.KEY_PRESSED && keyCode == 17) {
			ctrlPressed = true;
		} else if(!screen.getInteractions().isEmpty() && (id == KeyEvent.KEY_PRESSED || id == KeyEvent.KEY_TYPED)) {
			MyInteraction.keyPressed(id, keyCode, keyChar, screen.getSubWindows().lastElement());
		}
	}
	
	private static boolean isInArea(int x, int y, Canvas lastElement) {
		int xLow = lastElement.getOrigineX() - 4 ;
		int yLow = lastElement.getOrigineY() - 4;
		// "+4" is for resize -> canvas.resize methods
		int xHigh = xLow + lastElement.getWidth() + 8 ;
		int yHigh = yLow + lastElement.getHeight() + 8;
		
		if( x >= xLow && x <= xHigh && y >= yLow && y <= yHigh) {
			return true;
		}
		return false;
	}
}
