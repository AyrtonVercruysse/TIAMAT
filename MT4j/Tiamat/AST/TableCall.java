package AST;

public class TableCall extends Node{
	String name;

	public TableCall(Node parent, TableDefinition table) {
		super(parent);
		children.add(new Placeholder(this, "Integer", false));
		this.name = table.getName();
	}
	public String getName(){
		return name;
	}
	@Override
	public String toString(){
		String string = name + "[" + children.get(0).toString() + "]";
		return string;
	}

}
