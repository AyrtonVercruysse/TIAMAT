package rendering;

import org.mt4j.MTApplication;
import org.mt4j.components.TransformSpace;
import org.mt4j.components.visibleComponents.shapes.MTRectangle;
import org.mt4j.components.visibleComponents.widgets.MTTextArea;
import org.mt4j.util.math.Vector3D;

public class Value extends Renderer<AST.Value>{
	MTTextArea textArea;
	public Value(MTApplication mtApplication, AST.Value ast) {
		super(mtApplication, ast);
		String contentName = ast.getName();
		textArea = makeTextArea(mtApplication, contentName, blue);
	}
	public void display(MTRectangle parent, Vector3D position) {
		float width;
		float height;
		parent.addChild(drawing);
		drawing.addChild(textArea);
		drawing.setPositionRelativeToParent(position);
		width = textArea.getWidthXY(TransformSpace.RELATIVE_TO_PARENT);
		height = textArea.getHeightXY(TransformSpace.RELATIVE_TO_PARENT);
		drawing.setHeightLocal(height);
		drawing.setWidthLocal(width);
	}
		@Override
	public void unselect() {
		drawing.setStrokeColor(white);
	}
}
