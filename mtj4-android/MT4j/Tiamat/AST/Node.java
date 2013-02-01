package AST;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Vector;

public class Node implements Serializable{
	Node parent;
	Vector<Node> children = new Vector<Node>();

	public Node(Node parent) {
		this.parent = parent;
	}

	public Node getParent() {
		return parent;
	}

	public boolean isRoot() {
		if (parent == null) {
			return true;
		} else {
			return false;
		}
	}

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

	public Vector<Node> getChildren() {
		return children;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

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
}
