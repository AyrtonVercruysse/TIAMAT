package rendering;

import java.util.Vector;

import org.mt4j.MTAndroidApplication;
import org.mt4j.components.TransformSpace;
import org.mt4j.components.visibleComponents.shapes.MTRectangle;
import org.mt4j.util.math.Vector3D;
/**
 * A class to render a table definition.
 * @author Ayrton Vercruysse
 *
 */
public class TableDefinition extends Renderer<AST.TableDefinition>{
	MTRectangle def;
	MTRectangle colequal;
	Vector<Renderer<?>> children;
	/**
	 * The initialisation of this class.	
	 * @param mtApplication
	 * @param ast		The AST of this node.
	 * @param children	A vector with all children rendered.
	 */
	public TableDefinition(MTAndroidApplication mtApplication, AST.TableDefinition ast, Vector<Renderer<?>> children) {
		super(mtApplication, ast);
		def = makeText(mtApplication, "def");
		colequal = makeText(mtApplication, ":=");
		this.children = children;
	}
	@Override
	public void display(MTRectangle parent,Vector3D position) {
		Vector3D newPos;
		parent.addChild(drawing);
		float defHeight;
		float width;
		float contentHeight;
		Renderer<?> functionName = children.get(0);
		Renderer<?> content = children.get(1);
		drawing.setPositionRelativeToParent(position);
		drawing.addChild(def);
		drawing.addChild(colequal);
        defHeight = def.getHeightXY(TransformSpace.RELATIVE_TO_PARENT);
        width = def.getWidthXY(TransformSpace.RELATIVE_TO_PARENT);
		newPos = new Vector3D(width, 0);
		functionName.display(drawing, newPos);
		width = width + functionName.getWidth();
		colequal.setPositionRelativeToParent(new Vector3D (width,0));
		content.display(drawing, new Vector3D(0,defHeight));
		contentHeight = content.getHeight();
		newPos = new Vector3D(0,defHeight);
		drawing.setHeightLocal(defHeight+contentHeight);
	}
}
