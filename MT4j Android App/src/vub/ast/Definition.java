package vub.ast;

import java.io.Serializable;

import org.w3c.dom.Element;

public class Definition extends Node implements Serializable {

	public Definition(Node parent) {
		super(parent);
		Node name = new Placeholder(this, "Function Name", false);
		Node content = new Placeholder(this, "content", false);
		addChild(name);
		addChild(content);
	}

	public Definition(Element template){
		super(null);
		System.out.println("Template in de Definition");
	}
	
	public Node getName() {
		return getChild(0);
	}

	public void setName(Node newName) {
		setChild(0, newName);
	}

	public Node getContent() {
		return getChild(1);
	}

	public void setContent(Node newContent) {
		setChild(1, newContent);
	}

	@Override
	public String toString() {
		String string = "def " + getChild(0).toString() + " := "
				+ getChild(1).toString();
		return string;
	}

}
