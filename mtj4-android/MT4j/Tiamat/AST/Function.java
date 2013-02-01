package AST;

import java.io.Serializable;

public class Function extends Node implements Serializable{
	String[] names;

	public Function(Node parent, String[] names, String[] contents) {
		super(parent);
		int numberOfChildren = names.length;
		this.names = names;
		for (int i = 0; i < numberOfChildren; i++){
			children.add(new Placeholder(this, contents[i], false));
		}
	}
	public String[] getNames(){
		return names;
	}
	
	@Override
	public String toString(){
		String string = "";
		for(int i = 0; i < names.length; i++){
			string = string + names[i] + " " + children.get(i).toString() + " ";
		}
		return string;
	}
}
