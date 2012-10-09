package AST;

public class FunctionDefinition extends Node{
	int NumberOfArguments;
	
	public FunctionDefinition(Node parent, int numberOfArguments) {
		super(parent);
		this.NumberOfArguments = numberOfArguments;
		children.add(new Placeholder(this, "name", false));
		children.add(new ArgumentList(this, numberOfArguments));
		children.add(new Begin(this));
	}
	public String getName(){
		return ((Value)children.get(0)).getName();
	}
	public ArgumentList getArgumentList(){
		return ((ArgumentList)children.get(1));
	}
	public void setArgumentList(int numberOfArguments){
		children.set(1, new ArgumentList(this, numberOfArguments));
	}
	public void setName(Value value){
		children.set(0, value);
	}
	@Override
	public String toString(){
		String string = "def" + children.get(0).toString()+ children.get(1).toString() + ":=" + children.get(2).toString();
		return string;
	}
	

}
