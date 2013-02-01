package vub.ast;

import java.io.Serializable;

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
