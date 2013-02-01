package vub.ast;

import java.io.Serializable;

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

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		String string = name + getChild(0).toString();
		return string;
	}

}
