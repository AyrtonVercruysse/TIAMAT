package AST;

public class Operation extends Node{
	String operator;

	public Operation(Node parent, String operator) {
		super(parent);
		this.operator = operator;
		children.add(new Placeholder(this, "Arg1", false));
		children.add(new Placeholder(this, "Arg2", false));
	}
	public String getOperator(){
		return operator;
	}
	@Override
	public String toString(){
		String string = "( " + children.get(0).toString() + operator +  children.get(1).toString() + ")";
		return string;
	}
}
