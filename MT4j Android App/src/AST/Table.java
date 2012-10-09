package AST;

import java.io.Serializable;

/**
 * The Table AST node.
 * 
 * @author Ayrton Vercruysse
 * 
 */
public class Table extends Node implements Serializable{
	/**
	 * Initializes the Table node.
	 * 
	 * @param parent
	 *            The parent of the node
	 * @param numberOfElements
	 *            The number of elements the table has.
	 */
	public Table(Node parent, int numberOfElements) {
		super(parent);
		for (int i = 0; i < numberOfElements; i++) {
			children.add(new Placeholder(this, "Ele " + Integer.toString(i),
					false));
		}
	}

	@Override
	public String toString() {
		String string;
		string = "[";
		for (int i = 0; i < children.size(); i++) {
			string = string + children.get(i).toString() + ",";
		}
		string = string + "]";
		return string;
	}

}
