package aspects;

import figures.FigureElement;
import figures.Point;
import figures.gui.FigureSurface;

public aspect Exercise1b implements Observer{

	@Override
	public void update(FigureElement fe) {
		System.out.println("Update:" + fe.toString());
	}

	pointcut pointCreation(Point p): execution(Point.new(..)) && this(p);

	after(Point p): pointCreation(p){
		p.addObserver(this);

	}

}
