package answers4;

import java.awt.Rectangle;

public aspect Answer2a {
	private Rectangle figures.Group.Bounds = null;

	pointcut ex1(): execution(* figures.Group.getBounds(..));

	Rectangle around(figures.Group group): ex1() && target(group){
		if (group.Bounds == null)
			group.Bounds = proceed(group);
		return group.Bounds;
	}
}
