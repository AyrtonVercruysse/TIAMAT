package rendering;

import java.util.Vector;

import org.mt4j.MTApplication;
import org.mt4j.components.TransformSpace;
import org.mt4j.components.visibleComponents.shapes.MTRectangle;
import org.mt4j.util.math.Vector3D;

public class FunctionDefinition extends Renderer<AST.FunctionDefinition>{
	MTRectangle def;
	MTRectangle colequal;
	Vector<Renderer<?>> children;
		
	public FunctionDefinition(MTApplication mtApplication, AST.FunctionDefinition ast, Vector<Renderer<?>> children) {
		super(mtApplication, ast);
		def = makeText(mtApplication, "def");
		colequal = makeText(mtApplication, ":=");
		this.children = children;	
	}

	@Override
	public void display(MTRectangle parent, Vector3D position) {
		Vector3D newPos;
		parent.addChild(drawing);
		float height;
		float width;
		Renderer functionName = children.get(0);
		Renderer arguments = children.get(1);
		Renderer body = children.get(2);
		drawing.setPositionRelativeToParent(position);
		drawing.addChild(def);
		drawing.addChild(colequal);
		width = def.getWidthXY(TransformSpace.RELATIVE_TO_PARENT);
		functionName.display(drawing, new Vector3D(width,0));
		width = width + functionName.getWidth();
		newPos = new Vector3D(width,0);
		arguments.display(drawing, newPos);
		width = width + arguments.getWidth();
		colequal.setPositionRelativeToParent(new Vector3D (width,0));
		width = width + colequal.getWidthXY(TransformSpace.RELATIVE_TO_PARENT);
		body.display(drawing, new Vector3D(width,0));
		width = width + body.getWidth();
		height = body.getHeight();
		drawing.setHeightLocal(height);
		drawing.setWidthLocal(width);
		
	}
}