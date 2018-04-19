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

		File directory = new File("./");
		try {
			path = directory.getCanonicalPath() + "/src/tests/recordings/";
		} catch (Exception e) {}
	}
	
	/**
	 * WINDOW 
	 */
	
	@Test
	public void addWindowTest() {
		MyCanvasWindow.replayRecording(path + "addWindow/addSingle", canvasWindow);
	}
	
	@Test
	public void addMultipleWindowsTest() {
		MyCanvasWindow.replayRecording(path + "addWindow/addMultiple", canvasWindow);
	}
	
	@Test
	public void moveSingleWindowTest() {
		MyCanvasWindow.replayRecording(path + "moveWindow/moveSingleWindow", canvasWindow);
	}

	@Test
	public void moveMultipleWindowTest() {
		MyCanvasWindow.replayRecording(path + "moveWindow/moveMultipleWindows", canvasWindow);
	}
	
	@Test
	public void resizeSingleWindowTest() {
		MyCanvasWindow.replayRecording(path + "resizeWindow/resizeSingle", canvasWindow);
	}

	@Test
	public void resizeMultipleWindowTest() {
		MyCanvasWindow.replayRecording(path + "resizeWindow/resizeMultiple", canvasWindow);
	}
	
	@Test
	public void closeSingleWindowTest() {
		MyCanvasWindow.replayRecording(path + "closeWindow/closeSingle", canvasWindow);
	}
	@Test
	public void closeMultipleWindowTest() {
		MyCanvasWindow.replayRecording(path + "closeWindow/closeMultiple", canvasWindow);
	}
	
	@Test
	public void addSingleInteractionTest() {
		MyCanvasWindow.replayRecording(path + "addInteraction/addSingle", canvasWindow);
	}
	@Test
	public void addMultipleInteractionTest() {
		MyCanvasWindow.replayRecording(path + "addInteraction/addMultiple", canvasWindow);
	}
	
	@Test
	public void switchViewToSeqTest() {
		MyCanvasWindow.replayRecording(path + "switchView/switchToSeq", canvasWindow);
	}
	@Test
	public void switchViewToComTest() {
		MyCanvasWindow.replayRecording(path + "switchView/switchToCom", canvasWindow);
	}
	
	/**
	 * LABEL 
	 */
	
	@Test
	public void editLabelSeqTest() {
		MyCanvasWindow.replayRecording(path + "editLabel/editLabelSeq", canvasWindow);
	}
	@Test
	public void editLabelComTest() {
		MyCanvasWindow.replayRecording(path + "editLabel/editLabelCom", canvasWindow);
	}
	@Test
	public void editLabelBackspaceTest() {
		MyCanvasWindow.replayRecording(path + "editLabel/editLabelBackspace", canvasWindow);
	}
	@Test
	public void editLabelInvalidTest() {
		MyCanvasWindow.replayRecording(path + "editLabel/editLabelInvalid", canvasWindow);
	}
	
	/**
	 * PARTY
	 */
	
	@Test
	public void addSinglePartySeqTest() {
		MyCanvasWindow.replayRecording(path + "addParty/addSingleSeq", canvasWindow);
	}
	@Test
	public void addMultiplePartySeqTest() {
		MyCanvasWindow.replayRecording(path + "addParty/addMultipleSeq", canvasWindow);
	}
	@Test
	public void addSinglePartyComTest() {
		MyCanvasWindow.replayRecording(path + "addParty/addSingleCom", canvasWindow);
	}
	@Test
	public void addMultiplePartyComTest() {
		MyCanvasWindow.replayRecording(path + "addParty/addMultipleCom", canvasWindow);
	}
	
	@Test
	public void setActorSeqTest() {
		MyCanvasWindow.replayRecording(path + "setPartyType/setActorSeq", canvasWindow);
	}
	@Test
	public void setActorComTest() {
		MyCanvasWindow.replayRecording(path + "setPartyType/setActorCom", canvasWindow);
	}
	@Test
	public void setObjectSeqTest() {
		MyCanvasWindow.replayRecording(path + "setPartyType/setObjectSeq", canvasWindow);
	}
	@Test
	public void setObjectComTest() {
		MyCanvasWindow.replayRecording(path + "setPartyType/setObjectCom", canvasWindow);
	}
	
	@Test
	public void moveSinglePartySeqTest() {
		MyCanvasWindow.replayRecording(path + "moveParty/moveSingleSeq", canvasWindow);
	}
	@Test
	public void moveSinglePartyComTest() {
		MyCanvasWindow.replayRecording(path + "moveParty/moveSingleCom", canvasWindow);
	}
	@Test
	public void moveMultiplePartySeqTest() {
		MyCanvasWindow.replayRecording(path + "moveParty/moveMultipleSeq", canvasWindow);
	}
	@Test
	public void moveMultiplePartyComTest() {
		MyCanvasWindow.replayRecording(path + "moveParty/moveMultipleCom", canvasWindow);
	}
	
	/**
	 * MESSAGE
	 */
	
	@Test
	public void addSingleMessageSeqTest() {
		MyCanvasWindow.replayRecording(path + "addMessage/addSingleMessageSeq", canvasWindow);
	}
	@Test
	public void addMultipleMessageSeqTest() {
		MyCanvasWindow.replayRecording(path + "addMessage/addMultipleMessageSeq", canvasWindow);
	}
	@Test
	public void addNestedMessageSeqTest() {
		MyCanvasWindow.replayRecording(path + "addMessage/addNestedMessageSeq", canvasWindow);
	}
	
	/**
	 * ELEMENT
	 */
	
	@Test
	public void selectPartySeqTest() {
		MyCanvasWindow.replayRecording(path + "selectElement/selectPartySeq", canvasWindow);
	}
	@Test
	public void selectMessageSeqTest() {
		MyCanvasWindow.replayRecording(path + "selectElement/selectMessageSeq", canvasWindow);
	}
	@Test
	public void selectLabelSeqTest() {
		MyCanvasWindow.replayRecording(path + "selectElement/selectLabelSeq", canvasWindow);
	}
	@Test
	public void selectPartyComTest() {
		MyCanvasWindow.replayRecording(path + "selectElement/selectPartyCom", canvasWindow);
	}
	@Test
	public void selectMessageComTest() {
		MyCanvasWindow.replayRecording(path + "selectElement/selectMessageCom", canvasWindow);
	}
	@Test
	public void selectLabelComTest() {
		MyCanvasWindow.replayRecording(path + "selectElement/selectLabelCom", canvasWindow);
	}
	
	@Test
	public void deletePartySeqTest() {
		MyCanvasWindow.replayRecording(path + "deleteElement/deletePartySeq", canvasWindow);
	}
	@Test
	public void deleteMessageSeqTest() {
		MyCanvasWindow.replayRecording(path + "deleteElement/deleteMessageSeq", canvasWindow);
	}
	@Test
	public void deletePartyComTest() {
		MyCanvasWindow.replayRecording(path + "deleteElement/deletePartyCom", canvasWindow);
	}
	@Test
	public void deleteMessageComTest() {
		MyCanvasWindow.replayRecording(path + "deleteElement/deleteMessageCom", canvasWindow);
	}
	
}
