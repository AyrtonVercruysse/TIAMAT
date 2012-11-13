package answers;

import java.awt.Rectangle;

import figures.FigureElement;

public aspect Answer5 {
	pointcut ex5(int x, int y): call(* figures.Point.move(int, int)) && args(x, y);

	void around(int dx, int dy, figures.Point p): ex5(dx,dy) && target(p){
		int oldx = p.getX();
		int oldy = p.getY();
		proceed(dx, dy, p);
		if (p.getX() - oldx != dx || p.getY() - oldy != dy)
			throw new IllegalStateException();
	}

	pointcut ex5b(int dx, int dy):call(* FigureElement.move(int, int)) && args(dx, dy);

	void around(int dx, int dy, figures.FigureElement f): ex5b(dx,dy) && target(f){
		Rectangle oldBounds = f.getBounds();
		proceed(dx, dy, f);
		if ((f.getBounds().x != oldBounds.x + dx) || (f.getBounds().y != oldBounds.y + dy))
			throw new IllegalStateException();
		/* move(int x, int y) 
          Deprecated. As of JDK version 1.1, replaced by setLocation(int, int).*/
	}
}
