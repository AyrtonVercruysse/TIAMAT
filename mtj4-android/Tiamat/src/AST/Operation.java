package AST;

public class Operation extends Node{
	String operator;

	public Operation(Node parent, String operator) {
		super(parent);
		this.operator = operator;
		children = new Node[2];
		children[0] = new Placeholder(this, "Arg1");
		children[1] = new Placeholder(this, "Arg2");
	}
	public String getOperator(){
		return operator;
	}
}
