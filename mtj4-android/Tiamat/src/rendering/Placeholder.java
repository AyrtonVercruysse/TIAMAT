package rendering;

import org.mt4j.AbstractMTApplication;
import org.mt4j.components.visibleComponents.shapes.MTRectangle;
import org.mt4j.components.visibleComponents.widgets.MTTextArea;
import org.mt4j.util.math.Vector3D;

public class Placeholder extends Renderer<AST.Placeholder>{
	MTTextArea textArea;
	public Placeholder(AbstractMTApplication mtApplication, AST.Placeholder ast) {
		super(mtApplication, ast);
		String contentName = node.getName();
		textArea = makeTextArea(mtApplication, contentName, red);
	}
	public void display(MTRectangle parent, Vector3D position) {
		parent.addChild(drawing);
		drawing.addChild(textArea);
		drawing.setPositionRelativeToParent(position);
	}
		@Override
	public void unselect() {
		drawing.setStrokeColor(white);
	}
}