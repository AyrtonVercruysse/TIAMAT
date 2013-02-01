package vub.rendering;

import java.util.Vector;

import org.mt4j.MTAndroidApplication;
import org.mt4j.components.TransformSpace;
import org.mt4j.components.visibleComponents.shapes.MTRectangle;
import org.mt4j.util.math.Vector3D;
/**
 * A class to render a table
 * @author Ayrton Vercruysse
 *
 */
public class Table extends Renderer<vub.ast.Table>{
	MTRectangle open;
	MTRectangle close;
	MTRectangle[] commas;
	Vector<Renderer<?>> children;
	vub.ast.Node ast;
	/**
	 * The initialisation of this class	
	 * @param mtApplication
	 * @param ast		The AST of this node.
	 * @param children	A vector with all the rendered children.
	 */
	public Table(MTAndroidApplication mtApplication, vub.ast.Table ast, Vector<Renderer<?>> children) {
		super(mtApplication, ast);
		this.ast = ast;
		open = makeTextArea(mtApplication, "[");
		close = makeTextArea(mtApplication, "]");
		this.children = children;
		commas = new MTRectangle[children.size()]; 
		for (int i = 0; i < children.size(); i++){
			commas[i] = makeTextArea(mtApplication, ",");
		}
	}

	@Override
	public void display(MTRectangle parent, Vector3D position) {
		parent.addChild(drawing);
		float width;
        float commaWidth;
        drawing.addChild(open);
        drawing.addChild(close);
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
        drawing.setHeightLocal(close.getHeightXY(TransformSpace.RELATIVE_TO_PARENT));
        drawing.setWidthLocal(width+close.getWidthXY(TransformSpace.RELATIVE_TO_PARENT));
		
	}
	
	@Override
	public MTRectangle display(){
		RenderManager renderManager= new RenderManager(mtApplication, ast);
		renderManager.render(open, "under", false);
        for(int i = 0; i < children.size(); i++){
        	Renderer<?> child = children.get(i);
        	renderManager.render(child.display(), "next", false);
           	if(i < children.size()-1){
        		renderManager.render(commas[i], "next", false);
        	}
        }

		
		return renderManager.render(close, "under", false);
	}
}
