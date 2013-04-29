package vub.ast;

import java.io.Serializable;

import org.w3c.dom.Element;

public class FunctionCall extends Node implements Serializable{
	String name;
	FunctionDefinition function;

	public FunctionCall(Node parent, FunctionDefinition function) {
		super(parent);
		name = function.getName();
		this.function = function;
		Node argumentList = new ArgumentList(this, function.NumberOfArguments);
		addChild(argumentList);
	}
	
	public FunctionCall(Element template){
		super(null);
		System.out.println("Template in de FunctionCall");
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		String string = name + getChild(0).toString();
		return string;
	}

}
