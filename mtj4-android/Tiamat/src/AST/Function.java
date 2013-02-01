package AST;

public class Function extends Node{
	String[] names;

	public Function(Node parent, String[] names, String[] contents) {
		super(parent);
		int numberOfChildren = names.length;
		this.names = names;
		children = new Node[numberOfChildren];
		for (int i = 0; i < numberOfChildren; i++){
			children[i] = new Placeholder(this, contents[i]);
		}
	}
	public String[] getNames(){
		return names;
	}
}
