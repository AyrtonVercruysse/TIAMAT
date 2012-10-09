package AST;

public class FunctionDefinition extends Node{
	int NumberOfArguments;
	
	public FunctionDefinition(Node parent, int numberOfArguments) {
		super(parent);
		this.NumberOfArguments = numberOfArguments;
		children = new Node[3];
		children[0] = new Placeholder(this, "name");
		children[1] = new ArgumentList(this, numberOfArguments);
		children[3] = new Begin(this);
	}
	public String getName(){
		return ((Value)children[0]).getName();
	}
	public ArgumentList getArgumentList(){
		return ((ArgumentList)children[1]);
	}
	

}
