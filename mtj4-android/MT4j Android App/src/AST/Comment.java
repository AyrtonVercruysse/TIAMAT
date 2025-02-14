package AST;

import java.io.Serializable;

import AST.Node;

/**
 * The Comment AST node.
 * 
 * @author Ayrton Vercruysse
 * 
 */
public class Comment extends Node implements Serializable{
	Node item;

	/**
	 * Intializes the comment node.
	 * 
	 * @param parent
	 *            The parent of the node.
	 * @param item
	 *            The node that has to be commented.
	 */
	public Comment(Node parent, Node item) {
		super(parent);
		children.add(item);
	}

	/**
	 * Gives the commented node.
	 * 
	 * @return Returns the commented node.
	 */
	public Node getContent() {
		return children.get(0);
	}

	/**
	 * Changes the commented node.
	 * 
	 * @param newContent
	 *            Changes to commented node to newContent.
	 */
	public void setContent(Node newContent) {
		children.set(0, newContent);
	}

	@Override
	public String toString() {
		String string = " ";
		return string;

	}
}
