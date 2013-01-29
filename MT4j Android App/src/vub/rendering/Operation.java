package vub.rendering;

import java.util.Vector;

import org.mt4j.MTAndroidApplication;
import org.mt4j.components.TransformSpace;
import org.mt4j.components.visibleComponents.shapes.MTRectangle;
import org.mt4j.util.math.Vector3D;
/**
 * A class to render an operation
 * @author Ayrton Vercruysse
 *
 */
public class Operation extends Renderer<vub.ast.Operation>{
	MTRectangle open;
	MTRectangle close;
	MTRectangle operator;
	Renderer<?> argument1;
	Renderer<?> argument2;
	vub.ast.Node ast;
/**
 * The initialisation of this class.
 * @param mtApplication
 * @param ast		The AST of this node.
 * @param children	A vector with all the children rendered.
 */
	public Operation(MTAndroidApplication mtApplication, vub.ast.Operation ast, Vector<Renderer<?>> children) {
		super(mtApplication, ast);
		this.ast = ast;
		open = makeTextArea(mtApplication, "(");
		close = makeTextArea(mtApplication, ")");
		operator = makeTextArea(mtApplication, ast.getOperator());
		argument1 = children.get(0);
		argument2 = children.get(1);
		
	}

	@Override
	public void display(MTRectangle parent, Vector3D position) {
		parent.addChild(drawing);
		float width;
		drawing.addChild(open);
		drawing.addChild(close);
		drawing.addChild(operator);
		drawing.setPositionRelativeToParent(position);
		width = open.getWidthXY(TransformSpace.RELATIVE_TO_PARENT);
		argument1.display(drawing, new Vector3D(width, 0));
		width = width + argument1.getWidth();
		operator.setPositionRelativeToParent(new Vector3D(width,0));
		width = width + operator.getWidthXY(TransformSpace.RELATIVE_TO_PARENT);
		argument2.display(drawing, new Vector3D(width, 0));
		width = width + argument2.getWidth();
		close.setPositionRelativeToParent(new Vector3D(width,0));
		width = width + close.getWidthXY(TransformSpace.RELATIVE_TO_PARENT);
		drawing.setHeightLocal(close.getHeightXY(TransformSpace.RELATIVE_TO_PARENT));
        drawing.setWidthLocal(width);
	}
	
	@Override
	public MTRectangle display(){
		RenderManager renderManager= new RenderManager(mtApplication, ast);
		renderManager.render(open, "next", false);
		renderManager.render(argument1.display(), "next", false);
		renderManager.render(operator, "next", false);
		renderManager.render(argument2.display(), "next", false);
		return renderManager.render(close, "next", false);
	}
}
