package Model;
import java.util.ArrayList;
import java.util.Stack;

/**
 * A class that represnts the complete screen. It keeps track of all interactions.
 * 
 * @author Kevin Lavrijssen, Wout Mees, Florent Nander Meijer, Robbe Keters
 *
 */
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
	
	/**
	 * Returns a collection of all interactions of this screen.
	 * @return		This screen's interactions.
	 */
	public ArrayList<Interaction> getInteractions() {
		return this.interactions;
	}
	
	/**
	 * Removes a given interaction from this screen.
	 * @param i		The interaction to remove.
	 */
	public void removeInteraction(Interaction i) {
		interactions.remove(i);
	}
	
	/**
	 * Returns a collection of all subwindows of this screen.
	 * @return		This screen's subwindows.
	 */
	public Stack<Canvas> getSubWindows() {
		return subWindows;
	}
	
	/**
	 * Sets this screen's collection of subwindows to the given collection.
	 * @param subWindows		The given collection of subwindows.
	 */
	public void setSubWindows(Stack<Canvas> subWindows) {
		this.subWindows = subWindows;
	}
	
	/**
	 * Sets this screen's collection of interactions to the given collection.
	 * @param interactions		The given collection of interactions.
	 */
	public void setInteractions(ArrayList<Interaction> interactions) {
		this.interactions = interactions;
	}
	
}
