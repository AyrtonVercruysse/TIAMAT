package AST;

public class Table extends Node{

	public Table(Node parent, int numberOfElements) {
		super(parent);
		children = new Node[numberOfElements];
		for (int i = 0; i < numberOfElements; i++){
			children[i] = new Placeholder(this, "Ele "+Integer.toString(i));
		}
	}

}
