package AST;

import java.io.Serializable;

/**
 * The ArgumentList AST node.
 * 
 * @author Ayrton Vercruysse
 * 
 */
public class ArgumentList extends Node implements Serializable{
	/**
	 * Creates an instance of the ArgumentList node
	 * 
	 * @param parent
	 *            The parentnode.
	 * @param numberOfArguments
	 *            The number of arguments wanted in the list.
	 */
	public ArgumentList(Node parent, int numberOfArguments) {
		super(parent);
		for (int i = 0; i < numberOfArguments; i++) {
			children.add(new Placeholder(this, "Arg" + Integer.toString(i),
					false));
		}
	}

	@Override
	public String toString() {
		String string;
		string = "(";
		for (int i = 0; i < children.size(); i++) {
			string = string + children.get(i).toString() + ",";
		}
		string = string + ")";
		return string;
	}
}
