package rendering;

import java.util.Vector;

import org.mt4j.AbstractMTApplication;
import org.mt4j.components.TransformSpace;
import org.mt4j.components.visibleComponents.shapes.MTRectangle;
import org.mt4j.components.visibleComponents.widgets.MTTextArea;
import org.mt4j.util.math.Vector3D;

public class Function extends Renderer{
	MTTextArea[] texts;
	Vector<Renderer<?>> children;
	public Function(AbstractMTApplication mtApplication, AST.Function ast, Vector<Renderer<?>> children){
		super(mtApplication, ast);
		this.children = children;
	    String[] names = ast.getNames();
	    texts = new MTTextArea[names.length];
		for (int i = 0; i < names.length; i++)
			texts[i] = makeText(mtApplication, names[i]);
	}

	public void display(MTRectangle parent, Vector3D position) {
		float currentHeight = 0;
		System.out.println("Display Funtion");
		Vector3D newTextPos;
		Vector3D newPlaceholderPos;
		parent.addChild(drawing);
		drawing.setPositionRelativeToParent(position);
		for (int i = 0; i < texts.length; i++){
			drawing.addChild(texts[i]);
			newTextPos = new Vector3D(0, currentHeight);
			texts[i].setPositionRelativeToParent(newTextPos);
			currentHeight = currentHeight  + texts[i].getHeightXY(TransformSpace.RELATIVE_TO_PARENT); 
			newPlaceholderPos = new Vector3D(0,currentHeight);
			children.get(i).display(drawing, newPlaceholderPos);
			currentHeight = currentHeight + children.get(i).getHeight();
		}
		drawing.setHeightLocal(currentHeight);
	}
	
	@Override
	public void unselect() {
		((MTRectangle)drawing).setStrokeColor(white);
	}
}

