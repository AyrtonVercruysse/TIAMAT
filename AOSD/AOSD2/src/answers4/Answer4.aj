package answers4;

import figures.FigureElement;

public aspect Answer4 {

	private figures.Group figures.FigureElement.GroupPointer = null;

	pointcut ex4(): execution(* figures.*.move(..));

	pointcut ex4b(FigureElement fig): execution(* figures.Group.add(FigureElement)) && args(fig);

	before(FigureElement figEl, figures.Group group): ex4b(figEl) && target(group){
		figEl.GroupPointer = group;
	}

	before(figures.FigureElement figEl): ex4() && target(figEl){
		while (figEl.GroupPointer != null) {
			A.aspectOf(figEl.GroupPointer).Var = null;
			figEl = figEl.GroupPointer; 
		}
	}
}


