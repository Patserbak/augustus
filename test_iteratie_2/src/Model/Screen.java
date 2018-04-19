package Model;
import java.util.ArrayList;
import java.util.Stack;

public class Screen {
	
	/////////////////// Singleton ///////////////////
	/*private static Screen instance = null;
	
	private Screen() {}
	
	private synchronized static void createInstance() {
		if(instance == null) {instance = new Screen();}
	}
	
	public static Screen getInstance() {
		if(instance==null) {createInstance();}
		return instance;
	}*/

	////////////////////////////////////////////////
	
	private Stack<Canvas> subWindows = new Stack<Canvas>();
	
	private ArrayList<Interaction> interactions = new ArrayList<Interaction>();
	
	public ArrayList<Interaction> getInteractions() {
		return this.interactions;
	}
	
	public void removeInteraction(Interaction i) {
		interactions.remove(i);
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
