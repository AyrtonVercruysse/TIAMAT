package vub.ast;

import java.io.Serializable;

import org.w3c.dom.Element;

public class Value extends Node implements Serializable{
	String name;
	String comments;
	public Value(Node parent, String name) {
		super(parent);
		this.name = name;
		type = "Value";
	}
	
	public Value(Element template){
		super(null);
		System.out.println("Template in de Value");
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
