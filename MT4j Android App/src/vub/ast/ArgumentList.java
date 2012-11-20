package vub.ast;

import java.io.Serializable;

public class ArgumentList extends Node implements Serializable {

	public ArgumentList(Node parent, int numberOfArguments) {
		super(parent);
		Node child;
		for (int i = 0; i < numberOfArguments; i++) {
			child = new Placeholder(this, "Arg" + Integer.toString(i), false);
			addChild(child);
		}
	}

	@Override
	public String toString() {
		String string;
		string = "(";
		for (int i = 0; i < numberOfChildren(); i++) {
			string = string + getChild(i).toString() + ",";
		}
		string = string + ")";
		return string;
	}
}
