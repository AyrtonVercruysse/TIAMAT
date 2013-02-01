package AST;

public class TableDefinition extends Node {

	public TableDefinition(Node parent) {
		super(parent);
		children = new Node[3];
		children[0] = new Placeholder(this, "name");
		children[1] = new Placeholder(this, "Number");
		children[2] = new Begin(this);
	}
	public Node getNumberOfElements(){
		return children[0];
	}
	public void setNumberOfElements(Node newNumberOfElements){
		children[0] = newNumberOfElements;
	}
	public String getName(){
		return ((Value)children[0]).getName();
	}
	
}
