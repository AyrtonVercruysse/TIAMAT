package rendering;

import java.util.Vector;

import org.mt4j.MTAndroidApplication;
import org.mt4j.components.TransformSpace;
import org.mt4j.components.visibleComponents.shapes.MTRectangle;
import org.mt4j.util.math.Vector3D;
/**
 * The class for rendering the Argument list.
 * @author Ayrton Vercruysse
 *
 */
public class ArgumentList extends Renderer<AST.ArgumentList>{
	MTRectangle open;
	MTRectangle close;
	MTRectangle[] commas;
	Vector<Renderer<?>> children;
	/**
	 * The initialisation of the class.	
	 * @param mtApplication
	 * @param ast		The ast of the ArgumentList.
	 * @param children	A vector with all renderered children.
	 */
	public ArgumentList(MTAndroidApplication mtApplication, AST.ArgumentList ast, Vector<Renderer<?>> children) {
		super(mtApplication, ast);
		open = makeText(mtApplication, "(");			// The opening bracet.
		close = makeText(mtApplication, ")");			// The closing bracket.
		this.children = children;
		commas = new MTRectangle[children.size()]; 		// The comma's to separate the arguements.
		for (int i = 0; i < children.size(); i++){
			commas[i] = makeText(mtApplication, ",");
		}
	}

	@Override
	public void display(MTRectangle parent, Vector3D position) {
		parent.addChild(drawing);
		float width;
        float commaWidth;
        drawing.addChild(open);
        drawing.addChild(close);
        drawing.setPositionRelativeToParent(position);		// The drawing gets set at the right position.
        commaWidth = commas[0].getWidthXY(TransformSpace.RELATIVE_TO_PARENT);
        width = open.getWidthXY(TransformSpace.RELATIVE_TO_PARENT);
        for(int i = 0; i < children.size(); i++){
        	Renderer<?> child = children.get(i);
        	child.display(drawing, new Vector3D (width, 0));
        	width = width + child.getWidth();
        	if(i < children.size()-1){
        		drawing.addChild(commas[i]);
        		commas[i].setPositionRelativeToParent(new Vector3D(width,0));
        		width = width + commaWidth;
        	}
        }
        close.setPositionRelativeToParent(new Vector3D(width,0));
        // The width and the hight of the surrounding drawing gets set.
        drawing.setHeightLocal(close.getHeightXY(TransformSpace.RELATIVE_TO_PARENT));
        drawing.setWidthLocal(width+close.getWidthXY(TransformSpace.RELATIVE_TO_PARENT));
		
	}
}
