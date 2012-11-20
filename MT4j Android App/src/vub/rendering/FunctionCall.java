package vub.rendering;

import java.util.Vector;

import org.mt4j.MTAndroidApplication;
import org.mt4j.components.TransformSpace;
import org.mt4j.components.visibleComponents.shapes.MTRectangle;
import org.mt4j.util.math.Vector3D;
/**
 * The class to render the call of a function.
 * @author Ayrton Vercruysse
 *
 */
public class FunctionCall extends Renderer<vub.ast.FunctionCall> {
	MTRectangle openBracket;
	MTRectangle closeBracket;
	MTRectangle name;
	Vector<Renderer<?>> children;
/**
 * The initialisation of this class
 * @param mtApplication
 * @param ast		The ast of the node
 * @param children	A vector with the rendered children of this node.
 */
	public FunctionCall(MTAndroidApplication mtApplication,
			vub.ast.FunctionCall ast, Vector<Renderer<?>> children) {
		super(mtApplication, ast);
		openBracket = makeText(mtApplication, "(");
		closeBracket = makeText(mtApplication, ")");
		name = makeText(mtApplication, ast.getName());
		this.children = children;
	}

	@Override
	public void display(MTRectangle parent, Vector3D position) {
		parent.addChild(drawing);
		float height;
		float width;
		drawing.setPositionRelativeToParent(position);
		drawing.addChild(openBracket);
		drawing.addChild(closeBracket);
		drawing.addChild(name);
		width = name.getWidthXY(TransformSpace.RELATIVE_TO_PARENT);
		openBracket.setPositionRelativeToParent(new Vector3D(width, 0));
		width = width
				+ openBracket.getWidthXY(TransformSpace.RELATIVE_TO_PARENT);
		children.get(0).display(drawing, new Vector3D(width, 0));
		width = children.get(0).getWidth();
		closeBracket.setPositionRelativeToParent(new Vector3D(width, 0));
		width = width
				+ closeBracket.getWidthXY(TransformSpace.RELATIVE_TO_PARENT);
		height = openBracket.getHeightXY(TransformSpace.RELATIVE_TO_PARENT);
		drawing.setHeightLocal(height);
		drawing.setWidthLocal(width);
	}
}
