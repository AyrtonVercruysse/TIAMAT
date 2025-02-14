package AST;

import java.io.Serializable;

/**
 * The function AST node.
 * 
 * @author Ayrton Vercruysse
 * 
 */
public class Function extends Node implements Serializable{
	String[] names;

	/**
	 * Initializes the function node.
	 * 
	 * @param parent
	 *            The parent of the node.
	 * @param names
	 *            An array of the names of the function parts
	 *            ("if","then","else" or "when", "discovered"
	 * @param contents
	 *            An array of names for the contents of the parts ("predicate",
	 *            "consequent" and "alternative" or "predicate" and "content")
	 */
	public Function(Node parent, String[] names, String[] contents) {
		super(parent);
		int numberOfChildren = names.length;
		this.names = names;
		for (int i = 0; i < numberOfChildren; i++) {
			children.add(new Placeholder(this, contents[i], false));
		}
	}

	/**
	 * Gives the list of names of the function
	 * 
	 * @return Return an array with the names of the function.
	 */
	public String[] getNames() {
		return names;
	}

	@Override
	public String toString() {
		String string = "";
		for (int i = 0; i < names.length; i++) {
			string = string + names[i] + " " + children.get(i).toString() + " ";
		}
		return string;
	}
}
