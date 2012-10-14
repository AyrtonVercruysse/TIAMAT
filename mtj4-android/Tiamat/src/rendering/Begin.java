package rendering;

import java.util.Vector;

import org.mt4j.AbstractMTApplication;
import org.mt4j.components.TransformSpace;
import org.mt4j.components.visibleComponents.shapes.MTRectangle;
import org.mt4j.util.math.Vector3D;


public class Begin extends Renderer<AST.Begin>{
	MTRectangle open;
	MTRectangle close;
	Vector<Renderer<?>> children;
		
	public Begin(AbstractMTApplication mtApplication, AST.Begin ast, Vector<Renderer<?>> children) {
		super(mtApplication, ast);
		System.out.println("In den beginne");
		open = makeText(mtApplication, "{");
		close = makeText(mtApplication, "}");
		this.children = children;
	}
	@Override
	public void display(MTRectangle parent,Vector3D position) {
		Vector3D newPos;
		parent.addChild(drawing);
		float openHeight;
		float closeHeight;
		float contentHeight;
		drawing.setPositionRelativeToParent(position);
		drawing.addChild(open);
		drawing.addChild(close);
        openHeight = open.getHeightXY(TransformSpace.RELATIVE_TO_PARENT);
        closeHeight = close.getHeightXY(TransformSpace.RELATIVE_TO_PARENT);
		newPos = new Vector3D(0, openHeight);
		children.get(0).display(parent, newPos);
		contentHeight = children.get(0).getHeight();
		newPos.setY(openHeight+contentHeight);
		close.setPositionRelativeToParent(newPos);
		drawing.setHeightLocal(openHeight+contentHeight+closeHeight);
	}
	
	@Override
	public void unselect() {
		((MTRectangle)drawing).setStrokeColor(white);
	}
}
