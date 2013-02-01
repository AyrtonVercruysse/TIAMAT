package vub.ast;

import java.io.Serializable;

public class TableDefinition extends Node implements Serializable {

	public TableDefinition(Node parent) {
		super(parent);
		Node name = new Placeholder(this, "Name");
		Node number = new Placeholder(this, "Number");
		Node begin = new Begin(this);
		addChild(name);
		addChild(number);
		addChild(begin);
	}

	public Node getNumberOfElements() {
		return getChild(1);
	}

	public void setNumberOfElements(Value newNumberOfElements) {
		setChild(1, newNumberOfElements);
	}

	public String getName() {
		return ((Value) getChild(0)).getName();
	}

	public void setName(Value name) {
		setChild(0, name);
	}

	@Override
	public String toString() {
		String string = "def " + getChild(0).toString() + "["
				+ getChild(1).toString() + "] := "
				+ getChild(2).toString();
		return string;
	}

}
