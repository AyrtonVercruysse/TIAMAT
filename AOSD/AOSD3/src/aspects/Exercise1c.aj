package aspects;

import figures.FigureElement;
import figures.Point;

public aspect Exercise1c implements Observer {

	@Override
	public void update(FigureElement fe) {
		figures.gui.FigureSurface canvas = figures.gui.Main.panel
				.getFigureSurface();
		canvas.repaint();
	}
	
	pointcut moveFE(Point t): execution(* FigureElement.*(..)) && this(t);	
	
	after(figures.FigureElement t): moveFE(t) {
		update(t);
	}

}
