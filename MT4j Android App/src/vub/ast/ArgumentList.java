package vub.ast;

import java.io.Serializable;

import org.w3c.dom.Element;

public class ArgumentList extends Node implements Serializable {

	public ArgumentList(Node parent, int numberOfArguments) {
		super(parent);
		Node child;
		for (int i = 0; i < numberOfArguments; i++) {
			child = new Placeholder(this, "Arg" + Integer.toString(i), false);
			addChild(child);
		}
	}
	
	public ArgumentList(Element template){
		super(null);
		System.out.println("Template in de ArgumentList");
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
