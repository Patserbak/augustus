package Model;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import Controller.Mouse;
import Model.Handler.AddInteractionHandler;
import Model.Handler.AddWindowHandler;
import Model.Handler.CloseWindowHandler;
import Model.Handler.EditLabelHandler;

public class Screen {
	
	public Screen() {}

	private Stack<Canvas> subWindows = new Stack<Canvas>();
	
	private ArrayList<Interaction> interactions = new ArrayList<Interaction>();
	
	private boolean ctrlPressed =  false;
	
	public ArrayList<Interaction> getInteractions() {
		return this.interactions;
	}
	
	public void removeInteraction(Interaction i) {
		interactions.remove(i);
	}
	
	
	
	public void mouseClicked(Mouse id, int x, int y) {
		// First check if a party label is left in a valid state + check if there are any interactions
		if (!interactions.isEmpty() && !EditLabelHandler.editLabelModeParty(subWindows.lastElement())) {
			// Determine Canvas 
			Canvas canvas = null;
			
			
			Stack<Canvas> findList = new Stack<Canvas>();
			findList.addAll(subWindows);
			boolean found = false;
			Canvas top = findList.lastElement();
			while(!findList.isEmpty() && !found) {
				canvas = findList.pop();
				if( isInArea(x, y, canvas)) {
					found = true;
				}
			}
			// Selected SubWindow must be placed on top of the stack of subWindows
			subWindows.remove(canvas);
			subWindows.push(canvas);
			
			// Check if the selected canvas was on top of the stack(active canvas)
			if( top == canvas) {
				// Delegate to Interaction
				canvas.getInteraction().mouseClicked(id, x, y, canvas);
			}
		}
		
		CloseWindowHandler.handle(subWindows);
		
		// Find any Empty Interaction(empty interaction =  canvas left) ==> delete empty Interaction
		ArrayList<Interaction> toBeDeletedInteraction = new ArrayList<Interaction>();
		for( Interaction i :interactions) {
			if(i.getSubWindows().isEmpty()) {
				toBeDeletedInteraction.add(i);
			}
		}
		for (Interaction i : toBeDeletedInteraction) {
			interactions.remove(i);
		}
	}
	
	private boolean isInArea(int x, int y, Canvas lastElement) {
		int xLow = lastElement.getOrigineX();
		int yLow = lastElement.getOrigineY();
		// "+4" is for resize -> canvas.resize methods
		int xHigh = xLow + lastElement.getWidth() +4 ;
		int yHigh = yLow + lastElement.getHeight() + 4;
		
		if( x >= xLow && x <= xHigh && y >= yLow && y <= yHigh) {
			return true;
		}
		return false;
	}
	
	public void keyPressed(int id, int keyCode, char keyChar) {

		if( ctrlPressed && keyCode == 78 && (id == KeyEvent.KEY_PRESSED || id == KeyEvent.KEY_TYPED)) {
			AddInteractionHandler.handle(interactions, subWindows);
		
		}else if ( !interactions.isEmpty() && ctrlPressed && keyCode == 68 && (id == KeyEvent.KEY_PRESSED || id == KeyEvent.KEY_TYPED) ) {
			AddWindowHandler.handle(subWindows);
			
		} else {
			ctrlPressed = false;
		}		
		if( id == KeyEvent.KEY_PRESSED && keyCode == 17) {
			ctrlPressed = true;
		} else if(!interactions.isEmpty() && (id == KeyEvent.KEY_PRESSED || id == KeyEvent.KEY_TYPED)) {
			subWindows.lastElement().getInteraction().keyPressed(id, keyCode, keyChar, subWindows.lastElement());
		}
	}

	public Stack<Canvas> getSubWindows() {
		return subWindows;
	}

	public void setSubWindows(Stack<Canvas> subWindows) {
		this.subWindows = subWindows;
	}

	public void setInteractions(ArrayList<Interaction> interactions) {
		this.interactions = interactions;
	}
	
}
