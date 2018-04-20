package Model;
/**
 * 
 * An invocation message in a diagram.
 * 
 * @author Kevin Lavrijssen, Wout Mees, Florent Nander Meijer, Robbe Keters
 *
 */
public class InvocationMessage extends Message {
	/**
	 * Constructor.
	 * @param lab				The new invocation message's label.
	 * @param resultMessage		The new invocation message's result message.
	 */
	public InvocationMessage(String lab, ResultMessage resultMessage) {
		super(lab);
		result = resultMessage;
	}

}
