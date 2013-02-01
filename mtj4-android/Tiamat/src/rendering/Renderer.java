package rendering;
import org.mt4j.AbstractMTApplication;
import org.mt4j.components.TransformSpace;
import org.mt4j.components.visibleComponents.shapes.MTRectangle;
import org.mt4j.components.visibleComponents.shapes.MTRectangle.PositionAnchor;
import org.mt4j.components.visibleComponents.widgets.MTTextArea;
import org.mt4j.input.inputProcessors.IGestureEventListener;
import org.mt4j.input.inputProcessors.MTGestureEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapProcessor;
import org.mt4j.sceneManagement.AbstractScene;
import org.mt4j.util.MTColor;
import org.mt4j.util.font.FontManager;
import org.mt4j.util.font.IFont;
import org.mt4j.util.math.Vector3D;

import Tiamat.StartTiamat;


import AST.Node;

public abstract class Renderer<T extends Node> extends AbstractScene {
	MTRectangle drawing;
	protected T node;	
	public Renderer(AbstractMTApplication mtApplication, T ast) {
		super(mtApplication, "Node:"+ast.getClass().getSimpleName());
		node = ast;
		System.out.println("Renderer called");
		drawing = new MTRectangle(0,0,200,200,mtApplication);
		drawing.setNoFill(true);
		drawing.setAnchor(PositionAnchor.UPPER_LEFT);
		drawing.registerInputProcessor(new TapProcessor(mtApplication, 25, true, 350));
		drawing.addGestureListener(TapProcessor.class, new IGestureEventListener() {
		public boolean processGestureEvent(MTGestureEvent ge) {
			TapEvent te = (TapEvent)ge;
			if (te.isDoubleTap()){
				if(StartTiamat.selected == null) {
					StartTiamat.selected = node;
					drawing.setStrokeColor(red);
				}else{
				 	if(drawing.getStrokeColor() == red){
				 	   drawing.setStrokeColor(white);
				 	   StartTiamat.selected = null;
				 				 		
				 	}else{
				 		drawing.setStrokeColor(red);
				 		RenderVisitor.mapping.get(StartTiamat.selected);
				 		StartTiamat.selected = node;
					}
				}
			}
			return false;
		}
		});
	}
	static MTColor white = new MTColor(255,255,255);
	static MTColor black = new MTColor(0,0,0);
	static MTColor red = new MTColor(255,0,0);
	static MTColor blue = new MTColor(0,0,255);
	
	static MTTextArea makeText(AbstractMTApplication mtApplication, String text){
		IFont fontArial = FontManager.getInstance().createFont(mtApplication, "arial.ttf", 
				20, 	//Font size
				black,  //Font fill color
				black);	//Font outline color
		MTTextArea temp = new MTTextArea(mtApplication, fontArial);
		temp.setAnchor(PositionAnchor.UPPER_LEFT);
		temp.setNoFill(true);
		temp.setText(text);
		temp.setPickable(false);
		return temp;
	}
	static MTTextArea makeTextArea(AbstractMTApplication mtApplication, String text, MTColor color){
		MTTextArea temp = makeText(mtApplication, text);
		temp.setFillColor(color);
		temp.setNoFill(false);
		temp.setPickable(true);
		return temp;
	}

	abstract public void display(MTRectangle general, Vector3D position);
	public float getHeight(){
		return drawing.getHeightXY(TransformSpace.RELATIVE_TO_PARENT);
	}
	public float getWidth(){
		return drawing.getWidthXY(TransformSpace.RELATIVE_TO_PARENT);
	}
	public Vector3D getPosition(){
		return drawing.getPosition(TransformSpace.RELATIVE_TO_PARENT);
	}
	public void unselect(){
	}
	@Override
	public void init() {}
	@Override
	public void shutDown() {}
}