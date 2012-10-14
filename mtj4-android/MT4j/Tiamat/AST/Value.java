package AST;

public class Value extends Node{
	String name;

	public Value(Node parent, String name) {
		super(parent);
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public void setName(String newName){
		name = newName;
	}
	@Override
	public String toString(){
		return name;
	}
}
