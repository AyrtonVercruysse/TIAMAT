package AST;
import AST.Node;

public class Begin extends Node{

	public Begin(Node parent) {
		super(parent);
		Node child = new Placeholder(this, "content");
		children = new Node[1];
		children[0] = child;
	}
	public Node getContent(){
		return children[0];
	}
	public void setContent(Node newContent){
		children[0] = newContent;
	}
}
