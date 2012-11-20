package vub.ast;

import java.io.Serializable;

public class TableCall extends Node implements Serializable{
	String name;

	public TableCall(Node parent, TableDefinition table) {
		super(parent);
		Node offsetPlaceholder = new Placeholder(this, "Integer");
		children.add(offsetPlaceholder);
		this.name = table.getName();
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		String string = name + "[" + getChild(0).toString() + "]";
		return string;
	}

}
