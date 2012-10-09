package AST;

public class Definition extends Node{

	public Definition(Node parent) {
		super(parent);
		Node name = new Placeholder(this, "Function Name");
		Node content = new Placeholder(this, "content");
		children = new Node[2];
		children[0] = name;
		children[1] = content;
	}
	
	public Node getName(){
		return children[0];
	}
	public void setName(Node newName){
		children[0]= newName;
	}
	public Node getContent(){
		return children[1];
	}
	public void setContent(Node newContent){
		children[1] = newContent;
	}

}
