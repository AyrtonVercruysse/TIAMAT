package answers;

import figures.FigureElement;

public aspect Answer3 {
	pointcut ex3(int n): (set(private int figures.Point._x) || set(private int figures.Point._y)) && args(n);
	 
	before(int n): ex3(n){
		if(n< 0)
			throw new IllegalArgumentException(" < 0");
		
	}
	
	pointcut ex3b(FigureElement fig) : call(* figures.Group.add(FigureElement)) && args(fig);
	before(FigureElement fig, FigureElement fig2) : ex3b(fig) && target(fig2)  {
		if ((fig == fig2 ) || (fig == null)) {
			throw(new IllegalArgumentException("Illegal group"));
		}
	}
}

	