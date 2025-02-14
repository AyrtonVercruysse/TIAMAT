package vub.rendering;

import java.util.Vector;

import org.mt4j.MTAndroidApplication;
import org.mt4j.components.TransformSpace;
import org.mt4j.components.visibleComponents.shapes.MTRectangle;
import org.mt4j.components.visibleComponents.widgets.MTTextArea;
import org.mt4j.util.math.Vector3D;

/**
 * The class to render a function.
 * 
 * @author Ayrton Vercruysse
 * 
 */
public class Function extends Renderer<vub.ast.Function> {
	MTRectangle[] texts;
	Vector<Renderer<?>> children;
	vub.ast.Node ast;

	/**
	 * The initialisation of this function.
	 * 
	 * @param mtApplication
	 * @param ast
	 *            The AST of this function.
	 * @param children
	 *            A vector with the rendered children of this function.
	 */
	public Function(MTAndroidApplication mtApplication, vub.ast.Function ast,
			Vector<Renderer<?>> children) {
		super(mtApplication, ast);
		this.ast = ast;
		this.children = children;
		String[] names = ast.getNames();
		texts = new MTTextArea[names.length];
		for (int i = 0; i < names.length; i++)
			texts[i] = makeTextArea(mtApplication, names[i]);
	}
	
	@Override
	public void display(MTRectangle parent, Vector3D position) {
		float currentHeight = 0;
		
		System.out.println("Display Funtion");
		Vector3D newTextPos;
		Vector3D newPlaceholderPos;
		parent.addChild(drawing);
		drawing.setPositionRelativeToParent(position);
		for (int i = 0; i < texts.length; i++) {
			drawing.addChild(texts[i]);
			newTextPos = new Vector3D(0, currentHeight);
			texts[i].setPositionRelativeToParent(newTextPos);
			currentHeight = currentHeight
					+ texts[i].getHeightXY(TransformSpace.RELATIVE_TO_PARENT);
			newPlaceholderPos = new Vector3D(50, currentHeight);
			children.get(i).display(drawing, newPlaceholderPos);
			currentHeight = currentHeight + children.get(i).getHeight();
		}
		drawing.setHeightLocal(currentHeight);
	}
	@Override
	public MTRectangle display(){
		int i;
		RenderManager renderManager= new RenderManager(mtApplication, ast);
		renderManager.render(texts[0], "next", false);
		Renderer<?> functionName;
		functionName = children.get(0);
		renderManager.render(functionName.display(), "next", false);
		for (i = 1; i < texts.length-1; i++) {
			functionName = children.get(i);
			renderManager.render(texts[i], "next", false);
			renderManager.render(functionName.display(), "next",false);
			
		}
		functionName = children.get(i);
		renderManager.render(texts[i], "next", false);
		return renderManager.render(functionName.display(), "next",false);
			
	}
			//drawing.addChild(texts[i]);
			//newTextPos = new Vector3D(0, currentHeight);
			//texts[i].setPositionRelativeToParent(newTextPos);
			//currentHeight = currentHeight
			//		+ texts[i].getHeightXY(TransformSpace.RELATIVE_TO_PARENT);
			//newPlaceholderPos = new Vector3D(50, currentHeight);
			//children.get(i).display(drawing, newPlaceholderPos);
			//currentHeight = currentHeight + children.get(i).getHeight();
		
}
