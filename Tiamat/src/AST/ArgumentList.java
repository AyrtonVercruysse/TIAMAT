package AST;

public class ArgumentList extends Node{

	public ArgumentList(Node parent, int numberOfArguments) {
		super(parent);
		children = new Node[numberOfArguments];
		for (int i = 0; i < numberOfArguments; i++){
			children[i] = new Placeholder(this, "Arg"+Integer.toString(i));
		}
	}

}
