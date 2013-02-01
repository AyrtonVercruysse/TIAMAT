package AST;

import java.io.Serializable;

/**
 * The Placeholder AST class.
 * 
 * @author Ayrton Vercruysse
 * 
 */
public class Placeholder extends Node implements Serializable{
	String name;
	Boolean everlasting;

	/**
	 * Initializes the placeholder node.
	 * 
	 * @param parent
	 *            The parent of the placeholder.
	 * @param name
	 *            The name of the placeholder.
	 * @param everlasting
	 *            True if the placeholder is everlasting, false otherwise.
	 *            (Everlasting = when the placeholder is replaced, a new
	 *            placeholder is added aswell).
	 */
	public Placeholder(Node parent, String name, Boolean everlasting) {
		super(parent);
		this.name = name;
		this.everlasting = everlasting;
	}

	/**
	 * Gives the name of the placeholder.
	 * 
	 * @return Returns the name of the placeholder.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Changes the name of the placeholder.
	 * 
	 * @param newName
	 *            The new name of the placeholder.
	 */
	public void setName(String newName) {
		name = newName;
	}

}
