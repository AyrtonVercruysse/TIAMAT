package vub.ast;

import java.io.Serializable;
import java.lang.reflect.Constructor;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

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
		try {
			type = "Value";
			NodeList args = template.getElementsByTagName("args").item(0).getChildNodes();
			Element argument = (Element) args.item(0);
			String argumentName = argument.getNodeName();
			this.name = name;
		} catch (Exception ex) {
			System.out.println("TemplatesError");
			ex.printStackTrace();
		}
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
