package tests;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import Controller.MyCanvasWindow;

public class UseCaseTests {

	MyCanvasWindow canvasWindow;
	String path;
	
	@Before
	public void setUp() throws Exception {
		canvasWindow = new MyCanvasWindow("Test");

		File directory = new File(".\\");
		try {
			path = directory.getCanonicalPath() + "\\src\\tests\\recordings\\";
		} catch (Exception e) {}
	}
	
	/**
	 * WINDOW 
	 */
	
	@Test
	public void addWindowTest() {
//		MyCanvasWindow.replayRecording("addWindow\\addWindow", canvasWindow);
	}
	
	@Test
	public void moveWindowTest() {
		
	}
	
	@Test
	public void resizeWindowTest() {
		
	}
	
	@Test
	public void closeWindowTest() {
		
	}
	
	@Test
	public void addInteractionTest() {
		
	}
	
	/**
	 * ELEMENT
	 */
	
	@Test
	public void selectElementTest() {
		
	}
	
	@Test
	public void deleteElementTest() {
		
	}
	
	/**
	 * PARTY
	 */
	
	@Test
	public void addPartyTest() {
		
	}
	
	@Test
	public void setPartyTypeTest() {
		
	}
	
	@Test
	public void movePartyTest() {

	}
	
	/**
	 * MESSAGE
	 */
	
	@Test
	public void addMessageTest() {
		
	}
	
	/**
	 * LABEL 
	 */
	
	@Test
	public void editLabelTest() {
//		MyCanvasWindow.replayRecording("editLabel\\editLabelPartySeq", canvasWindow);
//		MyCanvasWindow.replayRecording("editLabel\\editLabelPartyCom", canvasWindow);
//		MyCanvasWindow.replayRecording("editLabel\\editLabelMessageSeq", canvasWindow);
//		MyCanvasWindow.replayRecording("editLabel\\editLabelMessageCom", canvasWindow);
	}
	
	
}
