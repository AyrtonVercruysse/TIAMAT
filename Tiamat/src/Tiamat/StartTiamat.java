package Tiamat;

import java.util.Vector;

import org.mt4j.MTAndroidApplication;
import org.mt4j.components.visibleComponents.shapes.MTRectangle;

import AST.Node;

public class StartTiamat extends MTAndroidApplication {
	private static final long serialVersionUID = 1L;
	public static Node selected;
	public static MTRectangle general;
	public static Vector<Templates> operations = new Vector();
	public static Vector<Templates> functions = new Vector();
	static Vector myFunctions = new Vector();
	static Vector variables = new Vector();
		
	//public static void main(String[] args) {
	//	initialize();
	//}
	@Override
	public void startUp() {
		addScene(new Tiamat(this, "Tiamat"));
	}
}
