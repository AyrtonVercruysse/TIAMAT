package answers2;

public aspect Answer1 {
	
	pointcut ex1(): execution(public * figures.*.*(..)) && !execution(public * figures.*.*.*(..));
	
	before(): ex1(){
		support.Log.write(thisJoinPoint.toString());
	}

}
