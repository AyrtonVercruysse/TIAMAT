package vub.ast;

import java.io.Serializable;

public class Placeholder extends Node implements Serializable{
	String name;
	Boolean everlasting = false;

	public Placeholder(Node parent, String name, Boolean everlasting) {
		super(parent);
		this.name = name;
		this.everlasting = everlasting;
	}
	public Placeholder(Node parent, String name) {
		super(parent);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String newName) {
		name = newName;
	}

}
