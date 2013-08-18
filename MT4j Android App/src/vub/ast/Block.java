package vub.ast;

import java.io.Serializable;

import org.w3c.dom.Element;

public class Block extends Node implements Serializable {
	int numberOfParameters = 0;

	public Block(Node parent, int numberOfParameters) {
		super(parent);
		Node child;
		for (int i = 0; i < numberOfParameters; i++) {
			child = new Placeholder(this, "Par" + Integer.toString(i), false);
			addChild(child);
		}
		Node content = new Placeholder(this, "Content", true);
		this.numberOfParameters = numberOfParameters;
		children.add(content);
	}
	
	public Block(Node parent, String name) {
		super(parent);
		Node child = new Placeholder(this, "Par", true);
		addChild(child);
		Node content = new Placeholder(this, "Content", true);
		children.add(content);
	}
	
	public Block(Element template){
		super(null);
		Node child;
		for (int i = 0; i < numberOfParameters; i++) {
			child = new Placeholder(this, "Par" + Integer.toString(i), false);
			addChild(child);
		}
		Node content = new Placeholder(this, "Content", true);
		this.numberOfParameters = numberOfParameters;
		children.add(content);
	}

	public Node getContent() {
		return getChild(numberOfParameters);
	}

	public void setContent(Node newContent) {
		setChild(numberOfParameters, newContent);
	}

	public int getNumberOfParameters() {
		return numberOfParameters;
	}

	@Override
	public String toString() {
		String string;
		string = "|";
		for (int i = 0; i < numberOfChildren(); i++) {
			string = string + getChild(i).toString() + ",";
		}
		string = string + "|";
		return string;
	}

}
