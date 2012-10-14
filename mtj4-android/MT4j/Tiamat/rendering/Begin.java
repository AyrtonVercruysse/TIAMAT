package rendering;

import java.util.Vector;

import org.mt4j.MTApplication;
import org.mt4j.components.TransformSpace;
import org.mt4j.components.visibleComponents.shapes.MTRectangle;
import org.mt4j.util.math.Vector3D;


public class Begin extends Renderer<AST.Begin>{
	MTRectangle open;
	MTRectangle close;
	Vector<Renderer<?>> children;
		
	public Begin(MTApplication mtApplication, AST.Begin ast, Vector<Renderer<?>> children) {
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
		float height;
		drawing.setPositionRelativeToParent(position);
		drawing.addChild(open);
		drawing.addChild(close);
		openHeight = open.getHeightXY(TransformSpace.RELATIVE_TO_PARENT);
        closeHeight = close.getHeightXY(TransformSpace.RELATIVE_TO_PARENT);
        height = openHeight;
		newPos = new Vector3D(0, openHeight);
		for(int i = 0; i < children.size(); i++){
        	Renderer child = children.get(i);
        	child.display(drawing, new Vector3D (0, height));
        	height = height + child.getHeight();
        	
        }
        		//children.get(0).display(parent, newPos);
		contentHeight = children.get(0).getHeight();
		newPos.setY(height);
		close.setPositionRelativeToParent(newPos);
		drawing.setHeightLocal(height);
	}
	
	@Override
	public void unselect() {
		((MTRectangle)drawing).setStrokeColor(white);
	}
}
