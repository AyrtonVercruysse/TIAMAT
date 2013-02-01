package AST;

import java.io.Serializable;

/**
 * The FunctionDefinition AST node.
 * 
 * @author Ayrton Vercruysse
 * 
 */
public class FunctionDefinition extends Node implements Serializable{
	int NumberOfArguments;

	/**
	 * Initializes a FunctionDefinition node.
	 * 
	 * @param parent
	 *            The parent of the node.
	 * @param numberOfArguments
	 *            The number of arguments the definition has.
	 */
	public FunctionDefinition(Node parent, int numberOfArguments) {
		super(parent);
		this.NumberOfArguments = numberOfArguments;
		children.add(new Placeholder(this, "name", false));
		children.add(new ArgumentList(this, numberOfArguments));
		children.add(new Begin(this));
	}

	/**
	 * Gives the name of the function.
	 * 
	 * @return Returns the name of the function.
	 */
	public String getName() {
		return ((Value) children.get(0)).getName();
	}

	/**
	 * Gives a list of arguments of the function.
	 * 
	 * @return Returns the ArgumentList of the function.
	 */
	public ArgumentList getArgumentList() {
		return ((ArgumentList) children.get(1));
	}

	/**
	 * Sets the current ArgumentList to a new one with the given number of
	 * parameters.
	 * 
	 * @param numberOfArguments
	 *            The number of arguments that the new ArgumentList has.
	 */
	public void setArgumentList(int numberOfArguments) {
		children.set(1, new ArgumentList(this, numberOfArguments));
	}

	/**
	 * Changes the name of the function.
	 * 
	 * @param value
	 *            The new name of the function.
	 */
	public void setName(Value value) {
		children.set(0, value);
	}

	@Override
	public String toString() {
		String string = "def" + children.get(0).toString()
				+ children.get(1).toString() + ":="
				+ children.get(2).toString();
		return string;
	}

}
