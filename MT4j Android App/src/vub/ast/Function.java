package vub.ast;

import java.io.Serializable;
import java.lang.reflect.Constructor;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import vub.templates.Templates;
import vub.tiamat.StartTiamat;

public class Function extends Node implements Serializable {
	String[] names;
	
	public Function(Element template) {
		super(null);
		try {
			NodeList args = template.getElementsByTagName("args").item(0).getChildNodes();
			int nrOfArgs = args.getLength();
			String names[] = new String[nrOfArgs];
			vub.ast.Node contents[] = new vub.ast.Node[nrOfArgs];
			for (int j = 0; j < nrOfArgs; j++) {
				Element argument = (Element) args.item(j);
				names[j] = argument.getAttribute("name"); 
				argument = (Element) argument.getFirstChild();
				String argumentName = argument.getNodeName();
				Class argumentsTypes;
				argumentsTypes = Class.forName(argumentName);
				Constructor argumentConstructor = argumentsTypes.getConstructor(Element.class);
				vub.ast.Node aerg = (vub.ast.Node)argumentConstructor.newInstance(argument);
				contents[j] = aerg;
			}
			int numberOfChildren = names.length;
			this.names = names;
			Node child;
			for (int i = 0; i < numberOfChildren; i++) {
				child = contents[i];
				addChild(child);
			}
			
		} catch (Exception ex) {
			System.out.println("TemplatesError2");
			ex.printStackTrace();
		}
	}

	public Function(Node parent, String[] names, Node[] contents) {
		super(parent);
		int numberOfChildren = names.length;
		this.names = names;
		Node child;
		for (int i = 0; i < numberOfChildren; i++) {
			child = contents[i];
			addChild(child);
		}
	}

	public Function(Node parent, String[] names, String[] contents) {
		super(parent);
		int numberOfChildren = names.length;
		this.names = names;
		Node child;
		for (int i = 0; i < numberOfChildren; i++) {
			child = new Placeholder(this, contents[i], false);
			addChild(child);
		}
	}

	public String[] getNames() {
		return names;
	}

	@Override
	public String toString() {
		String string = "";
		for (int i = 0; i < names.length; i++) {
			string = string + names[i] + " " + getChild(i).toString() + " ";
		}
		return string;
	}
	
	@Override
	public void toXML(Element rootElement, Document doc){
	/*	<vub.ast.Function>
        <args>
            <arg name="if">
                <vub.ast.Placeholder name="condition"/>
            </arg>
            <arg name="then">
                <vub.ast.Placeholder name="consequent"/>
            </arg>
            <arg name="else">
                <vub.ast.Placeholder name="alternative"/>
            </arg>
        </args>
    </vub.ast.Function>*/
		
		// staff elements 
		//Element staff = doc.createElement("Staff");
		//rootElement.appendChild(staff);
 
		// set attribute to staff element
		//Attr attr = doc.createAttribute("id");
		//attr.setValue("1");
		//staff.setAttributeNode(attr);
 
		// shorten way
		// staff.setAttribute("id", "1");
		
		Element function = doc.createElement("vub.ast.Function");
		
		Element args  = doc.createElement("args");
		for(int i = 0; i < names.length; i++){
			
			Element arg = doc.createElement("arg");
			arg.setAttribute("name", names[i]);
			getChild(i).toXML(arg, doc);
			args.appendChild(arg);
		}
		function.appendChild(args);
		rootElement.appendChild(function);
					
	}
	
}
