package Tiamat;

import java.util.Vector;

import org.mt4j.MTApplication;
import org.mt4j.components.visibleComponents.shapes.MTRectangle;

import AST.Node;

public class StartTiamat extends MTApplication {
	private static final long serialVersionUID = 1L;
	public static Node selected;
	public static MTRectangle general;
	public static Vector<Templates> operations = new Vector<Templates>();
	public static Vector<Templates> functions = new Vector<Templates>();
	public static Vector<Templates> myFunctions = new Vector<Templates>();
	public static Vector<Templates> variables = new Vector<Templates>();
	public static Vector<Templates> definitions = new Vector<Templates>();
		
	@Override
	public void startUp() {
		addScene(new Tiamat(this, "Tiamat"));
	}
}
