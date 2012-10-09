package AST;
import AST.Node;

public class Begin extends Node{

	public Begin(Node parent) {
		super(parent);
		Node child = new Placeholder(this, "content", true);
		children.add((Node) child);
	}
	public Node getContent(){
		return children.get(0);
	}
	public void setContent(Node newContent){
		children.set(0, newContent);
	}
	@Override
	public String toString(){
		String string = "{" + children.get(0).toString() + "}";
		return string;
		
	}
}
