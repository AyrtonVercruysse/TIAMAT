package vub.rendering;

import java.util.Vector;

import org.mt4j.MTAndroidApplication;
import org.mt4j.components.visibleComponents.shapes.MTRectangle;
import org.mt4j.util.math.Vector3D;

/**
 * The class to render a comment.
 * 
 * @author Ayrton Vercruysse
 * 
 */
public class Comment extends Renderer<vub.ast.Comment> {
	Vector<Renderer<?>> children;

	/**
	 * Initializes the class
	 * 
	 * @param mtApplication
	 * @param ast
	 *            The AST of the node.
	 * @param children
	 *            A vector with the rendered children of the node.
	 */
	public Comment(MTAndroidApplication mtApplication, vub.ast.Comment ast,
			Vector<Renderer<?>> children) {
		super(mtApplication, ast);
		this.children = children;

	}

	@Override
	public void display(MTRectangle parent, Vector3D position) {
		parent.addChild(drawing);
		drawing.setPositionRelativeToParent(position);
		drawing.setNoFill(false);
		drawing.setFillColor(green);
		children.get(0).display(drawing, new Vector3D(0, 0));
		drawing.setHeightLocal(children.get(0).getHeight());
		drawing.setWidthLocal(children.get(0).getWidth());
	}
}