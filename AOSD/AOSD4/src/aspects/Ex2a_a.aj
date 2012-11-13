package aspects;

import ca.ubc.cs.spl.aspectPatterns.patternLibrary.ObserverProtocol;
import figures.FigureElement;
import figures.gui.FigureSurface;

public aspect Ex2a_a extends ObserverProtocol {

	declare parents: FigureElement implements Subject;
	declare parents: FigureSurface implements Observer;

	
    protected pointcut subjectChange(Subject s): execution(* FigureElement.*(..)) && this(s);
	@Override
	
	protected void updateObserver(Subject subject, Observer observer) {
		((FigureSurface)observer).repaint();
	}
}
