package vub.ast;

import java.io.Serializable;

public class Value extends Node implements Serializable{
	String name;
	String comments;
	public Value(Node parent, String name) {
		super(parent);
		this.name = name;
		type = "Value";
	}

	public String getName() {
		return name;
	}

	public void setName(String newName) {
		name = newName;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
