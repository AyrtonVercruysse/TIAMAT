package Tiamat;

public class Templates {
	String names;
	AST.Node function;
	public Templates(String name, AST.Node function){
		names = name;
		this.function = function;
	}
	public Templates(String name, AST.Function function){
		
	}
	public String getName(){
		return names;
	}
	public AST.Node getFunction() {
		return function;
	}	
}

