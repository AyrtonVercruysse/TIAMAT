package rendering;

import org.mt4j.MTAndroidApplication;
import org.mt4j.components.TransformSpace;
import org.mt4j.components.visibleComponents.shapes.MTRectangle;
import org.mt4j.components.visibleComponents.widgets.MTTextArea;
import org.mt4j.util.math.Vector3D;
/**
 * A class to render a placeholder.
 * @author Ayrton Vercruysse
 *
 */
public class Placeholder extends Renderer<AST.Placeholder>{
	MTTextArea textArea;
	/**
	 * The initialsation of this class.
	 * @param mtApplication
	 * @param ast	The AST of this node.
	 */
	public Placeholder(MTAndroidApplication mtApplication, AST.Placeholder ast) {
		super(mtApplication, ast);
		String contentName = node.getName();
		textArea = makeTextArea(mtApplication, contentName, red);
	}
	@Override
	public void display(MTRectangle parent, Vector3D position) {
		float width;
		float height;
		parent.addChild(drawing);
		drawing.addChild(textArea);
		drawing.setPositionRelativeToParent(position);
		width = textArea.getWidthXY(TransformSpace.RELATIVE_TO_PARENT);
		height = textArea.getHeightXY(TransformSpace.RELATIVE_TO_PARENT);
		textArea.setPositionRelativeToParent(new Vector3D(1,1));
		drawing.setHeightLocal(height+2);
		drawing.setWidthLocal(width+2);
	}
}