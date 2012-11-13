package aspects;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import figures.FigureElement;

public aspect Exercise1a {
	private Set<Observer> figures.FigureElement.observers = new HashSet<Observer>();

	public void figures.FigureElement.addObserver(Observer obs) {
		observers.add(obs);
	};

	public void figures.FigureElement.notifyObservers() {
		Iterator<Observer> iter = observers.iterator();
		while (iter.hasNext()) {
			((Observer) iter.next()).update(this);
		}
	};

	pointcut ex1a(): call(* FigureElement.move(int, int));

	after(FigureElement fe): ex1a() && target(fe){
		fe.notifyObservers();
	}

}
