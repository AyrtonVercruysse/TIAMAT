package answers4;

import java.awt.Rectangle;

import figures.FigureElement;
import figures.Group;

public aspect Answer1 {
	pointcut ex1(): execution(* Group.getBounds(..));
	
	Rectangle around(): ex1(){
		Rectangle bounds = FigureElement.MAX_BOUNDS;
		return bounds;
		
	}

}
