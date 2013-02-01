package AST;

public class Placeholder extends Node{
	String name;

	public Placeholder(Node parent, String name) {
		super(parent);
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public void setName(String newName){
		name = newName;
	}

}
