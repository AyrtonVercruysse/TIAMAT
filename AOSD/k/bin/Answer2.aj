package answers;

aspect Answer2 {
	
	pointcut ex2(): set(private * *) && !withincode(void set*(..)) && !withincode(new(..));
	  declare warning: ex2(): "Bad field set";
	}
