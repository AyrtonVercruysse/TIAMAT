package AST;

import java.io.Serializable;

/**
 * The Operation AST node.
 * 
 * @author Ayrton Vercruysse
 * 
 */
public class Operation extends Node implements Serializable{
	String operator;

	/**
	 * Initializes the operation node
	 * 
	 * @param parent
	 *            The parent of this node.
	 * @param operator
	 *            The operatorsign of this operation (+,-,/,<,>,=,...).
	 */
	public Operation(Node parent, String operator) {
		super(parent);
		this.operator = operator;
		children.add(new Placeholder(this, "Arg1", false));
		children.add(new Placeholder(this, "Arg2", false));
	}

	/**
	 * Gives the operator of this operation.
	 * 
	 * @return Returns the operator
	 */
	public String getOperator() {
		return operator;
	}

	@Override
	public String toString() {
		String string = "( " + children.get(0).toString() + operator
				+ children.get(1).toString() + ")";
		return string;
	}
}
