package AST;

import java.io.Serializable;

/**
 * The TableCall AST node.
 * 
 * @author Ayrton Vercruysse
 * 
 */
public class TableCall extends Node implements Serializable{
	String name;

	/**
	 * Initializes the TableCall node.
	 * 
	 * @param parent
	 *            The parent of the node.
	 * @param table
	 *            The table on which this call is made.
	 */
	public TableCall(Node parent, TableDefinition table) {
		super(parent);
		children.add(new Placeholder(this, "Integer", false));
		this.name = table.getName();
	}

	/**
	 * Gives name of the Table on which this call is made
	 * 
	 * @return Returns the name of the table.
	 */
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		String string = name + "[" + children.get(0).toString() + "]";
		return string;
	}

}
