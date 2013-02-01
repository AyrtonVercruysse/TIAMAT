package AST;

public class FunctionCall extends Node{
	String name;
	
	public FunctionCall(Node parent, FunctionDefinition function) {
		super(parent);
		name = function.getName();
		children[0] = new ArgumentList(this, function.NumberOfArguments);
	}
	public String getName(){
		return name;
	}

}
