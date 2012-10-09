package rendering;

import java.util.Vector;

import org.mt4j.AbstractMTApplication;
import org.mt4j.components.TransformSpace;
import org.mt4j.components.visibleComponents.shapes.MTRectangle;
import org.mt4j.util.math.Vector3D;

public class Operation extends Renderer<AST.Operation>{
	MTRectangle open;
	MTRectangle close;
	MTRectangle operator;
	Renderer<?> argument1;
	Renderer<?> argument2;

	public Operation(AbstractMTApplication mtApplication, AST.Operation ast, Vector<Renderer<?>> children) {
		super(mtApplication, ast);
		open = makeText(mtApplication, "(");
		close = makeText(mtApplication, ")");
		operator = makeText(mtApplication, ast.getOperator());
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
		width = open.getWidthXY(TransformSpace.RELATIVE_TO_PARENT);
		argument1.display(drawing, new Vector3D(width, 0));
		width = width + argument1.getWidth();
		operator.setPositionRelativeToParent(new Vector3D(width,0));
		width = width + operator.getWidthXY(TransformSpace.RELATIVE_TO_PARENT);
		argument2.display(drawing, new Vector3D(width, 0));
		width = width + argument2.getWidth();
		close.setPositionRelativeToParent(new Vector3D(width,0));
		width = close.getWidthXY(TransformSpace.RELATIVE_TO_PARENT);
		drawing.setHeightLocal(close.getHeightXY(TransformSpace.RELATIVE_TO_PARENT));
        drawing.setWidthLocal(width);
	}
	

}
