package vub.ast;

import java.io.Serializable;

public class Function extends Node implements Serializable{
	String[] names;

	public Function(Node parent, String[] names, Node[] contents) {
		super(parent);
		int numberOfChildren = names.length;
		this.names = names;
		Node child;
		for (int i = 0; i < numberOfChildren; i++) {
			child = contents[i];
			addChild(child);
		}
	}
	public Function(Node parent, String[] names, String[] contents) {
		super(parent);
		int numberOfChildren = names.length;
		this.names = names;
		Node child;
		for (int i = 0; i < numberOfChildren; i++) {
			child = new Placeholder(this, contents[i], false);
			addChild(child);
		}
	}


	public String[] getNames() {
		return names;
	}

	@Override
	public String toString() {
		String string = "";
		for (int i = 0; i < names.length; i++) {
			string = string + names[i] + " " + getChild(i).toString() + " ";
		}
		return string;
	}
}
