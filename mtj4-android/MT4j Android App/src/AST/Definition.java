package AST;

import java.io.Serializable;

/**
 * The Definition AST class.
 * 
 * @author Ayrton Vercruysse
 * 
 */
public class Definition extends Node implements Serializable{
	/**
	 * Initializes the Definition node
	 * 
	 * @param parent
	 *            The parent of the node.
	 */
	public Definition(Node parent) {
		super(parent);
		Node name = new Placeholder(this, "Function Name", false);
		Node content = new Placeholder(this, "content", false);
		children.add(name);
		children.add(content);
	}

	/**
	 * Gives the name of the definition.
	 * 
	 * @return Returns the names of the definition.
	 */
	public Node getName() {
		return children.get(0);
	}

	/**
	 * Changes the name of the definition
	 * 
	 * @param newName
	 *            Sets the name of the definition.
	 */
	public void setName(Node newName) {
		children.set(0, newName);
	}

	/**
	 * Gives the content of the definition.
	 * 
	 * @return Returns the content of the definition.
	 */
	public Node getContent() {
		return children.get(1);
	}

	/**
	 * Changes the content of the definition.
	 * 
	 * @param newContent
	 *            Changes the content of the definition to newContent.
	 */
	public void setContent(Node newContent) {
		children.set(1, newContent);
	}

	@Override
	public String toString() {
		String string = "def " + children.get(0).toString() + " := "
				+ children.get(1).toString();
		return string;
	}

}
