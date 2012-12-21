package answers2;

import figures.FigureElement;
import figures.Group;
import figures.Point;

public aspect Answer3 {
	private FigureElement figures.Point.inGroup = null;

	pointcut ex3(FigureElement f): execution(* figures.Group.add(FigureElement)) && args(f);

	before(FigureElement f): ex3(f){
		if (f instanceof Point)
			support.Log.write("adding Point");
	}

	before(FigureElement f, FigureElement f2): ex3(f) && target(f2){
		if (f instanceof figures.Point) {
			if (((Point) f).inGroup != null) {
				throw (new IllegalStateException(((Point) f).inGroup.toString()));
			} else {
				((Point) f).inGroup = f2;
			}
		}
	}
}
