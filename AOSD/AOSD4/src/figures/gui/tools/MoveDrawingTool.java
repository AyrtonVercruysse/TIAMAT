package figures.gui.tools;

import figures.FigureElement;

public class MoveDrawingTool extends DrawingTool {
	FigureElement selected;
	
	public MoveDrawingTool(FigureElement selected, int x, int y) {
		super(x,y);
		this.selected = selected;
	}

	public DrawingTool update(int x, int y) {
		int dx = x - getX(), dy = y - getY();
		selected.move(dx,dy);
		setX(x); setY(y);
		return this;
	}
	
}
