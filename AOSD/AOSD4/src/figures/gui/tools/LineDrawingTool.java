package figures.gui.tools;

import figures.Group;
import figures.Line;
import figures.Point;

public class LineDrawingTool extends DrawingTool {
	
	Group canvas;
	private Point from;
	
	private Point newPoint;
	private Line newLine;
	
	public Point getPoint() {
		return from;
	}

	public Point getNewPoint() {
		return this.newPoint;
	}
	
	public Line getNewLine() {
		return this.newLine;
	}
	
	public void createPoint() {
		newPoint = new Point(getX(),getY());
		canvas.add(getNewPoint());
	}

	public void createLine() {
		Point from = getPoint();
		createPoint();
		Point to = getNewPoint();
		newLine = new Line(from,to);
		canvas.add(getNewLine());
	}

	public LineDrawingTool(Group canvas, int x, int y) {
		super(x,y);
		this.canvas = canvas;
		createPoint();
		this.from = getNewPoint();
	}

	public DrawingTool update(int x, int y) {
		if(validLine(x,y)) {
			setX(x);
			setY(y);
			createLine();
			return new MoveDrawingTool(getNewLine().getP2(),getX(),getY());
		} else {
			setX(x);
			setY(y);
			return this;
		}
	}

	public boolean validLine(int x, int y) {
		int dx = x - getX(), dy = y - getY();
		return !(Math.abs(dx) < 5 || Math.abs(dy) < 5);
	}
}
