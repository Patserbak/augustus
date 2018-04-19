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
		canvasWindow = new MyCanvasWindow("My Canvas Window");

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
		MyCanvasWindow.replayRecording(path + "addWindow\\addSingle", canvasWindow);
	}
	
	@Test
	public void addMultipleWindowsTest() {
		MyCanvasWindow.replayRecording(path + "addWindow\\addMultiple", canvasWindow);
	}
	
	@Test
	public void moveWindowTest() {
		MyCanvasWindow.replayRecording(path + "moveWindow\\moveSingleWindow", canvasWindow);
		MyCanvasWindow.replayRecording(path + "moveWindow\\moveMultipleWindows", canvasWindow);
	}
	
	@Test
	public void resizeWindowTest() {
		MyCanvasWindow.replayRecording(path + "resizeWindow\\resizeSingle", canvasWindow);
		MyCanvasWindow.replayRecording(path + "resizeWindow\\resizeMultiple", canvasWindow);
	}
	
	@Test
	public void closeWindowTest() {
		MyCanvasWindow.replayRecording(path + "closeWindow\\closeSingle", canvasWindow);
		MyCanvasWindow.replayRecording(path + "closeWindow\\closeMultiple", canvasWindow);
	}
	
	@Test
	public void addInteractionTest() {
		MyCanvasWindow.replayRecording(path + "addInteraction\\addSingle", canvasWindow);
		MyCanvasWindow.replayRecording(path + "addInteraction\\addMulitple", canvasWindow);
	}
	
	/**
	 * LABEL 
	 */
	
	@Test
	public void editLabelTest() {
		MyCanvasWindow.replayRecording(path + "editLabel\\editLabelSeq", canvasWindow);
		MyCanvasWindow.replayRecording(path + "editLabel\\editLabelCom", canvasWindow);
		MyCanvasWindow.replayRecording(path + "editLabel\\editLabelBackspace", canvasWindow);
		MyCanvasWindow.replayRecording(path + "editLabel\\editLabelInvalid", canvasWindow);
	}
	
	/**
	 * PARTY
	 */
	
	@Test
	public void addPartyTest() {
		MyCanvasWindow.replayRecording(path + "addParty\\addSingleSeq", canvasWindow);
		MyCanvasWindow.replayRecording(path + "addParty\\addMultipleCom", canvasWindow);
		MyCanvasWindow.replayRecording(path + "addParty\\addSingleSeq", canvasWindow);
		MyCanvasWindow.replayRecording(path + "addParty\\addMultipleCom", canvasWindow);
	}
	
	@Test
	public void setPartyTypeTest() {
		MyCanvasWindow.replayRecording(path + "setPartyType\\setActorSeq", canvasWindow);
//		MyCanvasWindow.replayRecording(path + "setPartyType\\setActorCom", canvasWindow);
//		MyCanvasWindow.replayRecording(path + "setPartyType\\setObjectSeq", canvasWindow);
//		MyCanvasWindow.replayRecording(path + "setPartyType\\setObjectCom", canvasWindow);
	}
	
	@Test
	public void movePartyTest() {
		MyCanvasWindow.replayRecording(path + "moveParty\\moveSingleSeq", canvasWindow);
		MyCanvasWindow.replayRecording(path + "moveParty\\moveSingleCom", canvasWindow);
		MyCanvasWindow.replayRecording(path + "moveParty\\moveMultipleSeq", canvasWindow);
		MyCanvasWindow.replayRecording(path + "moveParty\\moveMultipleCom", canvasWindow);
	}
	
	/**
	 * MESSAGE
	 */
	
	@Test
	public void addMessageTest() {
		MyCanvasWindow.replayRecording(path + "addMessage\\addSingleMessageSeq", canvasWindow);
		MyCanvasWindow.replayRecording(path + "addMessage\\addSingleMessageCom", canvasWindow);
		MyCanvasWindow.replayRecording(path + "addMessage\\addMultipleMessageSeq", canvasWindow);
		MyCanvasWindow.replayRecording(path + "addMessage\\addNestedMessageSeq", canvasWindow);
		MyCanvasWindow.replayRecording(path + "addMessage\\addMultipleMessageCom", canvasWindow);
	}
	
	/**
	 * ELEMENT
	 */
	
	@Test
	public void selectElementTest() {
		MyCanvasWindow.replayRecording(path + "selectElement\\selectPartySeq", canvasWindow);
		MyCanvasWindow.replayRecording(path + "selectElement\\selectMessageSeq", canvasWindow);
		MyCanvasWindow.replayRecording(path + "selectElement\\selectLabelSeq", canvasWindow);
		MyCanvasWindow.replayRecording(path + "selectElement\\selectPartyCom", canvasWindow);
		MyCanvasWindow.replayRecording(path + "selectElement\\selectMessageCom", canvasWindow);
		MyCanvasWindow.replayRecording(path + "selectElement\\selectLabelCom", canvasWindow);
	}
	
	@Test
	public void deleteElementTest() {
		MyCanvasWindow.replayRecording(path + "deleteElement\\deletePartySeq", canvasWindow);
		MyCanvasWindow.replayRecording(path + "deleteElement\\deleteMessageSeq", canvasWindow);
		MyCanvasWindow.replayRecording(path + "deleteElement\\deletePartyCom", canvasWindow);
		MyCanvasWindow.replayRecording(path + "deleteElement\\deleteMessageCom", canvasWindow);
	}
	
}
