package AST;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Vector;

import rendering.Comments;

/**
 * The AST node class.
 * 
 * @author Ayrton Vercruysse
 * 
 */
public class Node implements Serializable{
	Node parent;
	Comments comments;
	public String type;
	Vector<Node> children = new Vector<Node>();
	
	/**
	 * Initializes a node.
	 * 
	 * @param parent
	 *            The parent of the node.
	 */
	public Node(Node parent) {
		this.parent = parent;
		
	}

	/**
	 * Gives the parent of the node
	 * 
	 * @return Returns the parent of this node.
	 */
	public Node getParent() {
		return parent;
	}

	/**
	 * Checks if this node is the root node.
	 * 
	 * @return Returns true if this node is the root, otherwise false.
	 */
	public boolean isRoot() {
		if (parent == null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Sets a child to another child.
	 * 
	 * @param oldChild
	 *            The old child which has to be replaced.
	 * @param newChild
	 *            The child in which the old one has to be replaced.
	 */
	public void setChild(Node oldChild, Node newChild) {
		for (int i = 0; i < children.size(); i++) {
			if (children.get(i) == oldChild) {
				children.set(i, newChild);
				if (oldChild instanceof Placeholder
						&& ((Placeholder) oldChild).everlasting) {
					children.add(new Placeholder(this, "Content", true));
				}
			}
		}
	}

	/**
	 * Gives the childeren of the node.
	 * 
	 * @return Returns a vector with all childeren of the node.
	 */
	public Vector<Node> getChildren() {
		return children;
	}

	/**
	 * Changes the parent of this node to a new parent.
	 * 
	 * @param parent
	 *            The new parent.
	 */
	public void setParent(Node parent) {
		this.parent = parent;
	}

	/**
	 * Converts the node into a string.
	 */
	public String toString() {
		return null;
	}
	
	public Object clone() {
		ObjectOutputStream outStream = null;
		ObjectInputStream inStream = null;
		Object ret = false;
		System.out.println("Copy Called");
		try {
			System.out.println("in de Try");
			ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
			outStream = new ObjectOutputStream(byteOut);
			// serialize and write obj2DeepCopy to
			// byteOut
			System.out.println("This: " + this);
			outStream.writeObject(this);

			// always flush your stream
			outStream.flush();

			ByteArrayInputStream byteIn = new ByteArrayInputStream(
					byteOut.toByteArray());

			inStream = new ObjectInputStream(byteIn);
			ret = inStream.readObject();
			return ret;
		} catch (Exception e) {
			System.out.println("Exception 1");
			e.printStackTrace();
		} finally {
			// always close your streams in finally clauses
			try {
				outStream.close();
				inStream.close();
			} catch (IOException e) {
				System.out.println("Exception 2");
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				System.out.println("Exception 3");
				return (Node) ret;
			}
		}
		return null;
	}
	public Comments getComments(){
		return comments;
	};
	public void setComments(Comments comments){
		this.comments = comments;
	}

}
