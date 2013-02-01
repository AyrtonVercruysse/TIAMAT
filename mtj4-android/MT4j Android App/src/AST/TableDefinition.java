package AST;

import java.io.Serializable;

/**
 * The TableDefinition AST node.
 * 
 * @author Ayrton Vercruyesse
 * 
 */
public class TableDefinition extends Node implements Serializable{
	/**
	 * Initializes the node.
	 * 
	 * @param parent
	 *            The parent of the node.
	 */
	public TableDefinition(Node parent) {
		super(parent);
		children.add(new Placeholder(this, "Name", false));
		children.add(new Placeholder(this, "Number", false));
		children.add(new Begin(this));
	}

	/**
	 * Gives the number of elements of the table.
	 * 
	 * @return Returns the number of elements of the table.
	 */
	public Node getNumberOfElements() {
		return children.get(1);
	}

	/**
	 * Changes the number of elements of the table.
	 * 
	 * @param newNumberOfElements
	 *            The new number of elements of the table.
	 */
	public void setNumberOfElements(Value newNumberOfElements) {
		children.set(1, newNumberOfElements);
	}

	/**
	 * Gives the name of the table.
	 * 
	 * @return Returns the name of the table.
	 */
	public String getName() {
		return ((Value) children.get(0)).getName();
	}

	/**
	 * Changes the name of the table.
	 * 
	 * @param name
	 *            The new name of the table.
	 */
	public void setName(Value name) {
		children.set(0, name);
	}

	@Override
	public String toString() {
		String string = "def " + children.get(0).toString() + "["
				+ children.get(1).toString() + "] := "
				+ children.get(2).toString();
		return string;
	}

}
