package vub.ast;

import java.io.Serializable;

import org.w3c.dom.Element;

import vub.ast.Node;

public class Begin extends Node implements Serializable {
	public Begin(Node parent) {
		super(parent);
		Node child = new Placeholder(this, "content", true);
		addChild(child);
	}
	
	public Begin(Node parent, String content) {
		super(parent);
		Node child = new Placeholder(this, content, true);
		addChild(child);
	}
	
	public Begin(Element template){
		super(null);
		System.out.println("Template in de Begin");
	}

	public Node getContent() {
		return getChild(0);
	}

	public void setContent(Node newContent) {
		setChild(0, newContent);
	}

	@Override
	public String toString() {
		String string = "{";
		for (int i = 0; i < numberOfChildren(); i++) {
			string = string + getChild(i).toString() + ";";
		}
		string = string + "}";
		return string;

	}
	
	@Override
	public String toXML() {
		//String string = null;
		//for (int i = 0; i < numberOfChildren(); i++) {
		//	string = string + getChild(i).toXML();
		//}
		//return string;
		return getChild(0).toXML();
	}
}
