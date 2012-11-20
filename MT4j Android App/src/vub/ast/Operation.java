package vub.ast;

import java.io.Serializable;

public class Operation extends Node implements Serializable{
	String operator;

	public Operation(Node parent, String operator) {
		super(parent);
		this.operator = operator;
		Node argument1 = new Placeholder(this, "Arg1", false);
		Node argument2 = new Placeholder(this, "Arg2", false);
		addChild(argument1);
		addChild(argument2);
	}

	public String getOperator() {
		return operator;
	}

	@Override
	public String toString() {
		String string = "( " + getChild(0).toString() + operator
				+ getChild(1).toString() + ")";
		return string;
	}
}
