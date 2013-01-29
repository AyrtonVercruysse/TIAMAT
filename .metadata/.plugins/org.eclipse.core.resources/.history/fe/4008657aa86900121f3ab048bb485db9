package vub.rendering;

import org.mt4j.MTAndroidApplication;
import org.mt4j.components.TransformSpace;
import org.mt4j.components.visibleComponents.shapes.MTRectangle;
import org.mt4j.util.math.Vector3D;

/**
 * The class to render a value.
 * 
 * @author Ayrton Vercruysse
 * 
 */
public class Value extends Renderer<vub.ast.Value> {
	MTRectangle text;
	/**
	 * The initialsation of this class
	 * 
	 * @param mtApplication
	 * @param ast
	 *            The AST of this node.
	 */
	public Value(MTAndroidApplication mtApplication, vub.ast.Value ast) {
		super(mtApplication, ast);
		String contentName = ast.getName();
		text = makeTextArea(mtApplication, contentName, blue);
		Comments comments = new Comments(mtApplication, ast);
	}

	@Override
	public void display(MTRectangle parent, Vector3D position) {
		parent.addChild(drawing);
		drawing.setPositionRelativeToParent(position);
		drawing.addChild(text);
		drawing.setWidthLocal(text.getWidthXY(TransformSpace.RELATIVE_TO_PARENT));
		drawing.setHeightLocal(text.getHeightXY(TransformSpace.RELATIVE_TO_PARENT));
	}
}
