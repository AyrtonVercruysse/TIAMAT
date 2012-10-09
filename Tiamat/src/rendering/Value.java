package rendering;

import org.mt4j.AbstractMTApplication;
import org.mt4j.components.visibleComponents.shapes.MTRectangle;
import org.mt4j.util.math.Vector3D;

public class Value extends Renderer<AST.Value>{
	public Value(AbstractMTApplication mtApplication, AST.Value ast) {
		super(mtApplication, ast);
		String contentName = node.getName();
		drawing = makeTextArea(mtApplication, contentName, blue);
	}
	public void display(MTRectangle parent, Vector3D position) {
		parent.addChild(drawing);
		drawing.setPositionRelativeToParent(position);
	}
		@Override
	public void unselect() {
		drawing.setStrokeColor(white);
	}
}
