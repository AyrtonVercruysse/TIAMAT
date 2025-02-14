package vub.ast;

import java.io.Serializable;
import java.lang.reflect.Constructor;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import vub.templates.Templates;
import vub.tiamat.StartTiamat;

public class Operation extends Node implements Serializable{
	String operator;

	public Operation(Node parent, String operator, Node[] args) {
		super(parent);
		this.operator = operator;
		Node argument1 = new Placeholder(this, "Arg1", false);
		Node argument2 = new Placeholder(this, "Arg2", false);
		addChild(argument1);
		addChild(argument2);
	}
	
	public Operation(Node parent, String operator) {
		super(parent);
		this.operator = operator;
		Node argument1 = new Placeholder(this, "Arg1", false);
		Node argument2 = new Placeholder(this, "Arg2", false);
		addChild(argument1);
		addChild(argument2);
	}
	
	public Operation(Element template){
		super(null);
		try {
			String operator = template.getElementsByTagName("operator").item(0).getTextContent();
			NodeList args = template.getElementsByTagName("args").item(0).getChildNodes();
			System.out.println("Templ: " + operator);
			this.operator = operator;
			int nrOfArgs = args.getLength();
			String names[] = new String[nrOfArgs];
			vub.ast.Node contents[] = new vub.ast.Node[nrOfArgs];
			for (int j = 0; j < nrOfArgs; j++) {
				Element argument = (Element) args.item(j);
				//names[j] = argument.getAttribute("name");
				argument = (Element) argument.getFirstChild();
				String argumentName = argument.getNodeName();
				Class argumentsTypes;
				argumentsTypes = Class.forName(argumentName);
				Constructor argumentConstructor = argumentsTypes.getConstructor(Element.class);
				vub.ast.Node aerg = (vub.ast.Node)argumentConstructor.newInstance(argument);
				addChild(aerg);
			}
			
			
		} catch (Exception ex) {
			System.out.println("TemplatesError");
			ex.printStackTrace();
		}
	}

	public String getOperator() {
		return operator;
	}

	@Override
	public String toString() {
		String string = "( " + getChild(0).toString() + operator
				+ getChild(1).toString() + ")";
		return string;
	}
}
