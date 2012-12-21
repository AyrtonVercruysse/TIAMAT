import java.util.Iterator;

public aspect Basics {

	public void output(String tag, Object o) {
		System.out.println(tag + ": " + o);
	}

	pointcut ex1(): call(* start());

	before(): ex1() {
		output("ex1", "About to start");
	};

	pointcut ex2(): call(* st*(..));

	before(): ex2() {
		output("ex2", thisJoinPoint.getSignature());
	}
	
	pointcut ex3(): execution(!static * Application.*(..)) && !execution(* Application.start(..));
	before(): ex3(){
		output("ex 3", thisJoinPoint.getSignature());
	}
	
	pointcut ex4(int n): execution(* Application.stop(int)) && args(n);
	
	before(int n): ex4(n){
		output ("ex4", n);
	}
	
	pointcut ex5(): cflow (execution(* Application.start(..))) && execution(* Application.stop(..));
	before(): ex5(){
		output("ex5", "Stop called from start");
	}
	
	pointcut ex6(): execution(* *(..));
	after() throwing(Exception e):  ex6(){
		output("ex6", e.getMessage());
	}
	
	
	declare parents: Application implements Iterable<String>;
	public String Application.name = "MyApplication";
	
	public Iterator<String> Application.iterator(){
		return java.util.Arrays.asList(name, "go: " + go).iterator();
	}
	
}
