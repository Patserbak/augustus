package View;

import java.awt.Graphics;

import Model.Canvas;

public class ViewContext {

	private ViewState myView;
	
	public ViewContext() {
		setState(new SequenceState());
	}
	
	public void draw(Canvas canvas, Graphics graphics) {
		myView.draw(this, canvas, graphics);
	}
	
	private void setState(ViewState viewState) {
		myView = viewState;
	}
}
