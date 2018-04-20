package Model;

/**
 * An enumeration used to indicate what part of a subwindow is being resized during the window resizing process.
 * 
 * @author Kevin Lavrijssen, Wout Mees, Florent Nander Meijer, Robbe Keters
 */
public enum Window {
	ResizeXRight,
	ResizeXLeft,
	ResizeYTop,
	ResizeYLower,
	ResizeLowerRightCorner,
	ResizeLowerLeftCorner,
	ResizeTopLeftCorner,
	ResizeTopRightCorner
}
