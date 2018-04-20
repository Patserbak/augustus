package Controller;

import java.awt.event.KeyEvent;

import Model.Canvas;
import Model.Interaction;
import Model.Message;
import Model.Mode;
import Model.Party;
import Model.Handler.AddMessageHandler;
import Model.Handler.AddPartyHandler;
import Model.Handler.CloseWindowHandler;
import Model.Handler.DeleteElementHandler;
import Model.Handler.EditLabelHandler;
import Model.Handler.Handler;
import Model.Handler.MovePartyHandler;
import Model.Handler.SelectElementHandler;
import Model.Handler.SetPartyTypeHandler;
import Model.Handler.SwitchViewHandler;

/**
 * A class that processes input related to the Interaction class.
 * 
 * @author Kevin Lavrijssen, Wout Mees, Florent Nander Meijer, Robbe Keters
 */
public class MyInteraction {
	
	/**
	 * Handles actions affecting a certain Screen and a certain canvas initiated by a mouse event.
	 * @param id			The kind of mouse event.
	 * @param x				The x coordinate of the mouse event.
	 * @param y				The y coordinate of the mouse event.
	 * @param canvas		The canvas to be handled and edited.
	 * @param interaction	The interaction to be handled and edited.
	 */
	public static void mouseClicked(Mouse id, int x, int y, Canvas canvas, Interaction interaction) {
		
		if(canvas.getMode()==Mode.ADDMESSAGE) {SelectElementHandler.deselectAll(canvas);}
		
		switch(id){
		
		case RELEASED:
			if(canvas.getMode()==Mode.ADDMESSAGE) {System.out.println("######## Handling Message ########");AddMessageHandler.handle(canvas, x, y);}
			if(canvas.getMode()==Mode.ADDMESSAGE || canvas.getMode()==Mode.MOVEPARTY) {SelectElementHandler.handle(canvas, x, y, Mouse.RELEASED);break;}
		case DRAGGED:
			if(canvas.getMode()==Mode.MOVEPARTY) {MovePartyHandler.handle(canvas, x, y);break;}		
		case PRESSED:
			SelectElementHandler.handle(canvas, x, y, Mouse.PRESSED);break;	
		
		case SINGLECLICK:
			SelectElementHandler.handle(canvas, x, y, Mouse.SINGLECLICK);break; 
			
		case DOUBLECLICK:
				if(!EditLabelHandler.editLabelModeMessage(canvas)) {
					if(Handler.getPartyAt(x, y, canvas)!=null){SetPartyTypeHandler.handle(canvas, x, y);break;}
					else{AddPartyHandler.handle(canvas, x, y);break;}
				}
		}
		
		CloseWindowHandler.handle(interaction.getSubWindows());
		
	}
	
	/**
	 * Handles actions affecting a certain canvas initiated by a mouse event.
	 * @param id		The kind of key event.
	 * @param keyCode	The code of the key pressed.
	 * @param keyChar	The key pressed.
	 * @param canvas	The canvas to be handled and edited.
	 */
	public static void keyPressed(int id, int keyCode, char keyChar, Canvas canvas) {
		
		if(id == KeyEvent.KEY_PRESSED && !EditLabelHandler.editLabelModeParty(canvas)) {
			switch(keyCode){
			case KeyEvent.VK_TAB:
				System.out.println("TAB");
				SwitchViewHandler.handle(canvas);
				break;
			case KeyEvent.VK_ENTER:
				System.out.println("ENTER");
				break;

			case KeyEvent.VK_DELETE:
				System.out.println("DELETE");
				DeleteElementHandler.handle(canvas);
			default:
				break;
			}
		} else if (id == KeyEvent.KEY_TYPED) {
			for(Party p : canvas.getParties()){
				if(p.getLabel().getSelected()) {
					if(canvas.getView() == Canvas.View.SEQUENCE)
						EditLabelHandler.handle(canvas, p.getLabel(), p, keyChar, p.getLabel().getLabelPositionSequence().getX(), p.getLabel().getLabelPositionSequence().getY());
					else 
						EditLabelHandler.handle(canvas, p.getLabel(), p, keyChar, p.getLabel().getLabelPositionComm().getX(), p.getLabel().getLabelPositionComm().getY());
					break;
				}
			}
			for(Message m : canvas.getMessages()){
				if(m.getLabel().getSelected()) {
					if(canvas.getView() == Canvas.View.SEQUENCE)
						EditLabelHandler.handle(canvas, m.getLabel(), keyChar, m.getLabel().getLabelPositionSequence().getX(), m.getLabel().getLabelPositionSequence().getY());
					else 
						EditLabelHandler.handle(canvas, m.getLabel(), keyChar, m.getLabel().getLabelPositionComm().getX(), m.getLabel().getLabelPositionComm().getY());
					break;
				}
			}
		}
	}
	
}
