package vub.ast;

import java.io.Serializable;

public class Table extends Node implements Serializable{

	public Table(Node parent, int numberOfElements) {
		super(parent);
		Node child;
		for (int i = 0; i < numberOfElements; i++) {
			child = new Placeholder(this, "Ele " + Integer.toString(i));
			addChild(child);
		}
	}

	@Override
	public String toString() {
		String string;
		string = "[";
		for (int i = 0; i < numberOfChildren(); i++) {
			string = string + getChild(i).toString() + ",";
		}
		string = string + "]";
		return string;
	}

}
