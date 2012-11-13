package answers;

public aspect Answer4 {
	
	pointcut ex4(int n): (set(private int figures.Point._x) || set(private int figures.Point._y)) && args(n);
	
	
	void around(int val): ex4(val) {
	    if (val < 0)
	    	val = 0;
	    proceed(val);
	  }
	}
