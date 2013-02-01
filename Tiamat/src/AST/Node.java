package AST;

public class Node {
	Node parent;
	Node[] children;
	
	public Node(Node parent){
		this.parent = parent;
	}
	
	public Node getParent(){
		return parent;
	}
	public boolean isRoot(){
		if (parent == null){
			return true;
		}else { 
			return false;
		}
	}
	public void setChild(Node oldChild, Node newChild){
		for (int i = 0; i < children.length; i++){
			if(children[i]==oldChild){
				children[i]=newChild;
			}
		}
	}
	public Node[] getChildren(){
		return children;
	}
	public void setParent(Node parent){
		this.parent = parent;
	}
}
