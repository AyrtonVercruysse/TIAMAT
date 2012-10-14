package AST;

public class Placeholder extends Node{
	String name;
	Boolean everlasting;
	public Placeholder(Node parent, String name, Boolean everlasting) {
		super(parent);
		this.name = name;
		this.everlasting = everlasting;
	}
	public String getName(){
		return name;
	}
	public void setName(String newName){
		name = newName;
	}

}
