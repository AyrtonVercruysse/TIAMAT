package AST;

public class Block extends Node{
	int numberOfParameters;

	public Block(Node parent, int numberOfParameters) {
		super(parent);
		for(int i = 0; i < numberOfParameters; i++){
			children.add(new Placeholder(this, "Par"+Integer.toString(i), false));
		}
		Node content = new Placeholder(this, "Content", true);
		this.numberOfParameters = numberOfParameters;
		children.add(content);		
	}
	public Node getContent(){
		return children.get(numberOfParameters);
	}
	public void setContent(Node newContent){
		children.set(numberOfParameters, newContent);
	}
	public int getNumberOfParameters(){
		return numberOfParameters;
	}
	@Override
	public String toString(){
		String string;
		string = "|";
		for(int i = 0; i < children.size(); i++){
			string = string + children.get(i).toString()+ ",";
		}
		string = string + "|";
		return string;
	}

}
