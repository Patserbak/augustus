package View;

import java.awt.Graphics;

import Model.Canvas;

public interface ViewState {

	void draw(ViewContext viewContext, Canvas canvas, Graphics g);
	
}
