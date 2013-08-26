package vub.ast;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

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
	
	public Begin(Element template) throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException{
		super(null);
		NodeList args = template.getChildNodes();
		for (int i = 0; i < args.getLength(); i++) {
			Element temp = (Element) args.item(i); //Template
			String type = temp.getNodeName();
			Class argumentsTypes = Class.forName(type); // e.g. Function
			Constructor argumentConstructor = argumentsTypes.getConstructor(Element.class);
			vub.ast.Node func = (vub.ast.Node)argumentConstructor.newInstance(template); // Node
			System.out.println("Temp" + type);
			addChild(func);
		}
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
	public void toXML(Element rootElement, Document doc) {
		//String string = null;
		//for (int i = 0; i < numberOfChildren(); i++) {
		//	string = string + getChild(i).toXML();
		//}
		//return string;
		Element begin = doc.createElement("vub.ast.Begin");
		for (int i = 0; i < numberOfChildren(); i++) {
			getChild(i).toXML(begin, doc);
		}
		rootElement.appendChild(begin);
	}
}
