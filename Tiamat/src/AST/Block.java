package AST;

public class Block extends Node{
	int numberOfParameters;

	public Block(Node parent, int numberOfParameters) {
		super(parent);
		children = new Node[numberOfParameters+1];
		for(int i = 0; i < numberOfParameters; i++){
			children[i] = new Placeholder(this, "Par"+Integer.toString(i));
		}
		Node content = new Placeholder(this, "Content");
		this.numberOfParameters = numberOfParameters;
		children[numberOfParameters] = content;		
	}
	public Node getContent(){
		return children[numberOfParameters];
	}
	public void setContent(Node newContent){
		children[numberOfParameters]= newContent;
	}
	public int getNumberOfParameters(){
		return numberOfParameters;
	}

}
