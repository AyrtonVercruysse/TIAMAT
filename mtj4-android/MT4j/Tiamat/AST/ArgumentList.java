package AST;

public class ArgumentList extends Node{

	public ArgumentList(Node parent, int numberOfArguments) {
		super(parent);
		for (int i = 0; i < numberOfArguments; i++){
			children.add(new Placeholder(this, "Arg"+Integer.toString(i), false));
		}
	}
	@Override
	public String toString(){
		String string;
		string = "(";
		for(int i = 0; i < children.size(); i++){
			string = string + children.get(i).toString()+ ",";
		}
		string = string + ")";
		return string;
	}

}
