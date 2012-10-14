package AST;

import java.io.Serializable;

/**
 * The FunctionCall AST node.
 * 
 * @author Ayrton Vercruysse
 * 
 */
public class FunctionCall extends Node implements Serializable{
	String name;
	FunctionDefinition function;

	/**
	 * Initializes a FunctionCall node.
	 * 
	 * @param parent
	 *            The parent of the node.
	 * @param function
	 *            The function for what this function call is made.
	 */
	public FunctionCall(Node parent, FunctionDefinition function) {
		super(parent);
		name = function.getName();
		this.function = function;
		children.add(new ArgumentList(this, function.NumberOfArguments));
	}

	/**
	 * Gives the name of the function.
	 * 
	 * @return Returns the name of the function.
	 */
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		String string = name + children.get(0).toString();
		return string;
	}

}
