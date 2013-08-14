package vub.ast;

import java.io.Serializable;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class FunctionDefinition extends Node implements Serializable{
	int NumberOfArguments;

	public FunctionDefinition(Node parent, int numberOfArguments) {
		super(parent);
		this.NumberOfArguments = numberOfArguments;
		Node name = new Placeholder(this, "name", false);
		Node argumentList = new ArgumentList(this, numberOfArguments);
		Node begin = new Begin(this);
		addChild(name);
		addChild(argumentList);
		addChild(begin);
	}
	
	public FunctionDefinition(Element template){
		/*
		public Templates FunctionDefinition() {
		vub.ast.Node node = new vub.ast.FunctionDefinition(null, 0);
		return new Templates("Function", node);
		}
		 */
		super(null);
		try {
			NodeList args = template.getElementsByTagName("args").item(0).getChildNodes();
			Element argument = (Element) args.item(0);
			String argumentName = argument.getNodeValue();
			System.out.println("FuncDef" + argumentName);
		} catch (Exception ex) {
			System.out.println("TemplatesError");
			ex.printStackTrace();
		}
	}


	public String getName() {
		return ((Value) getChild(0)).getName();
	}

	public ArgumentList getArgumentList() {
		return ((ArgumentList) getChild(1));
	}

	public void setArgumentList(int numberOfArguments) {
		Node newArgumentList = new ArgumentList(this, numberOfArguments);
		setChild(1, newArgumentList);
	}

	public void setName(Value value) {
		setChild(0, value);
	}

	@Override
	public String toString() {
		String string = "def" + getChild(0).toString()
				+ getChild(1).toString() + ":="
				+ getChild(2).toString();
		return string;
	}

}
