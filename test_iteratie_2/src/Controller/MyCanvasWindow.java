package Controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;


import Controller.Mouse;
import Model.Canvas;
import Model.Screen;
import Model.Handler.EditLabelHandler;
import View.CommunicationDiagram;
import View.CommunicationState;
import View.SequenceDiagram;
import View.SequenceState;
import View.View;
import View.ViewContext;
import View.ViewState;

/**
 * A window for custom drawing.
 * This class is an extension of the class CanvasWindow.
 * 
 * @author Kevin Lavrijssen, Wout Mees, Florent Nander Meijer, Robbe Keters
 * @version 2.0
 *
 */
public class MyCanvasWindow extends CanvasWindow{
	
	private Screen screen = new Screen(); //Screen.getInstance();
	private MyScreen myScreen = new MyScreen(); //MyScreen.getInstance();
	
	/**
	 * The constructor for MyCanvasWindow.
	 * 
	 * @param title 		The title for this window.
	 */
	public MyCanvasWindow(String title) {
		super(title);
	}
	
	/**
	 * Paints the current window using the canvas data.
	 * 
	 * @param g 		The Graphics object used for painting the window.
	 */
	@Override
	protected void paint(Graphics g){
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, 600, 600);
		
		Color textColor = Color.BLACK;
		Font font = new Font("Font", 1, 12);
		
		g.setColor(textColor);
		g.setFont(font);
				
		for(Canvas c : screen.getSubWindows()) {
			ViewContext viewContext = new ViewContext();
			ViewState v;
			if(c.getView() == Canvas.View.SEQUENCE)
				v = new SequenceState();
			else 
				v = new CommunicationState();
			v.draw(viewContext, c, g);
//			View v;
//			if (c.getView() == Canvas.View.SEQUENCE)
//				v = new SequenceDiagram();
//			else
//				v = new CommunicationDiagram();
//			v.draw(c, g);
		}
	}
	
	/**
	 * Handles a recorded mouse event.
	 * 
	 * @param id 			The kind of mouse event to be handled.
	 * @param x 			The x coordinate clicked.
	 * @param y 			The y coordinate clicked.
	 * @param clickCount 	The amount of times clicked.
	 */
	@Override
	protected void handleMouseEvent(int id, int x, int y, int clickCount){
		// 
		if( !screen.getInteractions().isEmpty()) {
			Canvas canvas = screen.getSubWindows().lastElement();
			
			System.out.println("######## "+canvas.getMode()+" ########");
			
			if(!EditLabelHandler.editLabelModeParty(canvas)) {
				
				switch(id) {
					
				case MouseEvent.MOUSE_CLICKED: 
					if(clickCount == 1) {myScreen.mouseClicked(Mouse.SINGLECLICK, x, y, screen);}
					if(clickCount == 2) {myScreen.mouseClicked(Mouse.DOUBLECLICK, x, y, screen);}
					break;
				case MouseEvent.MOUSE_PRESSED:
					myScreen.mouseClicked(Mouse.PRESSED, x, y, screen);
					break;
				case MouseEvent.MOUSE_DRAGGED:
					myScreen.mouseClicked(Mouse.DRAGGED, x, y, screen);
					break;
				case MouseEvent.MOUSE_RELEASED:
					myScreen.mouseClicked(Mouse.RELEASED, x, y, screen);
					break;
				}
			}
		}
		repaint();
	
	}
	
	/**
	 * Handles an event where a key on the keyboard is pressed.
	 * 
	 * @param id 		The kind of key event.
	 * @param keyCode		The code of the key pressed.
	 * @param keyChar		The key pressed.
	 */
	@Override
	protected void handleKeyEvent(int id, int keyCode, char keyChar){
		myScreen.keyPressed(id, keyCode, keyChar, screen);
		repaint();
	}
	
	/**
	 * The main method.
	 * 
	 * @param args		Unused input.
	 */
	
	public static void main(String[] args) {
		
		MyCanvasWindow myCanvas = new MyCanvasWindow("My Canvas Window");
		
//		2 -- Create a recording
//	    String pathPrefix =
//		 "C:\\Users\\Gebruiker\\Documents\\SourceTree\\test_iteration_2\\test_iteratie_2\\src\\tests\\recordings\\";
//		 String newFile = "moveParty/moveMultipleCom";
//	     String filePath = pathPrefix + newFile;
//		 myCanvas.recordSession(filePath);
		
//		1 -- Run the interactrr
	     java.awt.EventQueue.invokeLater(() -> {
	        myCanvas.show();
		});
	 }
	
}
