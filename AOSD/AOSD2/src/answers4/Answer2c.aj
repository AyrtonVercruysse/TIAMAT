 package answers4;

import java.awt.Rectangle;

abstract aspect Answer2c<T, Y> perthis(ex2b()){
	T Var = null;

	abstract pointcut ex2();
	abstract pointcut ex2b();

	T around(Y y): ex2() && target(y){
		if (Var == null)
			Var = proceed(y);
		return Var;
	}
}
aspect A extends Answer2c<Rectangle, figures.Group>{
	pointcut ex2(): execution(* figures.Group.getBounds(..));
	pointcut ex2b():execution(figures.Group.new(..));

};
