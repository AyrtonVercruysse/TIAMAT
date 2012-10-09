package AST;

public class TableCall extends Node{
	String name;

	public TableCall(Node parent, TableDefinition table) {
		super(parent);
		children = new Node[1];
		children[0] = new Placeholder(this, "Integer");
		this.name = table.getName();
	}
	public String getName(){
		return name;
	}

}
