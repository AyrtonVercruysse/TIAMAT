package Menus;

import org.mt4j.AbstractMTApplication;
import org.mt4j.components.interfaces.IMTComponent3D;
import org.mt4j.components.visibleComponents.shapes.MTRectangle;
import org.mt4j.components.visibleComponents.shapes.MTRoundRectangle;
import org.mt4j.components.visibleComponents.shapes.MTRectangle.PositionAnchor;
import org.mt4j.components.visibleComponents.widgets.MTList;
import org.mt4j.components.visibleComponents.widgets.MTListCell;
import org.mt4j.components.visibleComponents.widgets.MTTextArea;
import org.mt4j.input.inputProcessors.IGestureEventListener;
import org.mt4j.input.inputProcessors.MTGestureEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapProcessor;
import org.mt4j.sceneManagement.AbstractScene;
import org.mt4j.util.MTColor;
import org.mt4j.util.animation.Animation;
import org.mt4j.util.animation.AnimationEvent;
import org.mt4j.util.animation.IAnimationListener;
import org.mt4j.util.animation.MultiPurposeInterpolator;
import org.mt4j.util.math.Vector3D;

import rendering.Begin;
import rendering.Function;
import rendering.RenderVisitor;
import rendering.Renderer;

import AST.Node;

import Tiamat.StartTiamat;

public abstract class Menu extends AbstractScene {
	boolean animationRunning = false;
	boolean doSlideIn = false;
	AST.Begin test = new AST.Begin(null);
	
	public Menu(AbstractMTApplication mtApplication, String name) {
		super(mtApplication, name);
		MTRectangle test = new MTRectangle(0,0,10,10, mtApplication);
		test.setFillColor(new MTColor(255,0,0));
		getCanvas().addChild(test);
	}
		
	
	abstract public void Make(AbstractMTApplication mtApplication, String name);
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void shutDown() {
		// TODO Auto-generated method stub
		
	}
}
