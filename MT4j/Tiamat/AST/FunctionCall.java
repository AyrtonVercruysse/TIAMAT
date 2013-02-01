package AST;

public class FunctionCall extends Node{
	String name;
	FunctionDefinition function;
	
	public FunctionCall(Node parent, FunctionDefinition function) {
		super(parent);
		name = function.getName();
		function = function;
		children.add(new ArgumentList(this, function.NumberOfArguments));
	}
	public String getName(){
		return name;
	}
	@Override
	public String toString(){
		String string = name + children.get(0).toString();
		return string;
	}

}
