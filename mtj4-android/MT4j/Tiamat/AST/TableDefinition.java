package AST;

public class TableDefinition extends Node {

	public TableDefinition(Node parent) {
		super(parent);
		children.add(new Placeholder(this, "Name", false));
		children.add(new Placeholder(this, "Number", false));
		children.add(new Begin(this));
	}
	public Node getNumberOfElements(){
		return children.get(1);
	}
	public void setNumberOfElements(Value newNumberOfElements){
		children.set(1, newNumberOfElements);
	}
	public String getName(){
		return ((Value)children.get(0)).getName();
	}
	public void setName(Value name){
		children.set(0, name);
	}
	@Override
	public String toString(){
		String string = "def "+ children.get(0).toString() + "[" + children.get(1).toString() + "] := " + children.get(2).toString();
		return string;
	}
	
}
