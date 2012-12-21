package answers2;

import figures.FigureElement;

public aspect Answer2 {
	
	pointcut ex2(): call(public * figures.*.*(..)) && !call(public * figures.*.*.*(..))  && !within(Answer2);
	
	before(FigureElement f): ex2() && target(f){
		support.Log.write(thisJoinPoint.toString() + " at " +  f.toString());
	}

}
