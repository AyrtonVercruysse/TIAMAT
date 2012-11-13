package answers4;

import java.awt.Rectangle;

public aspect Answer2b perthis(ex1()){
	private Rectangle Bounds = null;

	pointcut ex1(): execution(* figures.Group.getBounds(..));

	Rectangle around(figures.Group group): ex1() && target(group){
		if (Bounds == null)
			Bounds = proceed(group);
		return Bounds;
	}
}
