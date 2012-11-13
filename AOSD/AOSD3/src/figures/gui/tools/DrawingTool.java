package figures.gui.tools;

import figures.Group;
import figures.FigureElement;

public abstract class DrawingTool {
	
	private int x, y;

	public DrawingTool(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	protected void setX(int x) {
		this.x = x;
	}
	
	protected void setY(int y) {
		this.y = y;
	}
	
	abstract public DrawingTool update(int x, int y);
	
	static public DrawingTool start(Group canvas, int x, int y) {
		FigureElement selected = canvas.findFigureElement(x,y);
		if(selected!=null) {
			return new MoveDrawingTool(selected,x,y);
		} else {
			return new LineDrawingTool(canvas,x,y);
		}
	}

}
