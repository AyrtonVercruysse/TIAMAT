package rendering;

import java.util.Vector;

import org.mt4j.MTApplication;
import org.mt4j.components.MTComponent;
import org.mt4j.components.TransformSpace;
import org.mt4j.components.visibleComponents.shapes.MTRectangle;
import org.mt4j.components.visibleComponents.shapes.MTRoundRectangle;
import org.mt4j.components.visibleComponents.shapes.MTRectangle.PositionAnchor;
import org.mt4j.components.visibleComponents.widgets.MTTextArea;
import org.mt4j.input.inputProcessors.IGestureEventListener;
import org.mt4j.input.inputProcessors.MTGestureEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapProcessor;
import org.mt4j.util.MTColor;
import org.mt4j.util.math.Vector3D;

import Tiamat.StartTiamat;


public class FunctionCall extends Renderer<AST.FunctionCall>{
	MTRectangle openBracket;
	MTRectangle closeBracket;
	MTRectangle name;
	Vector<Renderer<?>> children;
		
	public FunctionCall(MTApplication mtApplication, AST.FunctionCall ast, Vector<Renderer<?>> children) {
		super(mtApplication, ast);
		openBracket = makeText(mtApplication, "(");
		closeBracket = makeText(mtApplication, ")");
		name = makeText(mtApplication, ast.getName());
		this.children = children;
	}
	@Override
	public void display(MTRectangle parent,Vector3D position) {
		Vector3D newPos;
		parent.addChild(drawing);
		float height;
		float width;
		Renderer functionName = children.get(0);
		Renderer content = children.get(1);
		drawing.setPositionRelativeToParent(position);
		drawing.addChild(openBracket);
		drawing.addChild(closeBracket);
		drawing.addChild(name);
		width = name.getWidthXY(TransformSpace.RELATIVE_TO_PARENT);
		openBracket.setPositionRelativeToParent(new Vector3D (width,0));
		width = width + openBracket.getWidthXY(TransformSpace.RELATIVE_TO_PARENT);
		children.get(0).display(drawing, new Vector3D(width,0));
		width = children.get(0).getWidth();
		closeBracket.setPositionRelativeToParent(new Vector3D (width,0));
		width = width + closeBracket.getWidthXY(TransformSpace.RELATIVE_TO_PARENT);
		height = openBracket.getHeightXY(TransformSpace.RELATIVE_TO_PARENT);
		drawing.setHeightLocal(height);
		drawing.setWidthLocal(width);
	}
	
	@Override
	public void unselect() {
		drawing.setStrokeColor(white);
	}
}
