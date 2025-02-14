package vub.ast;

import java.io.Serializable;
import java.lang.reflect.Constructor;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import vub.templates.Templates;
import vub.tiamat.StartTiamat;

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
	
	public Placeholder(Element template) {
		super(null);
		try {
			this.name = template.getAttribute("name");
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
	public String toString(){
		return name;
	}
	
	@Override
	public void toXML(Element rootElement, Document doc){
		//  <vub.ast.Placeholder name="consequent"/>
		// staff elements 
		//<vub.ast.Placeholder name="condition"/>
		Element placeholder = doc.createElement("vub.ast.Placeholder");
		placeholder.setAttribute("name", name);
		rootElement.appendChild(placeholder);
	}

}
