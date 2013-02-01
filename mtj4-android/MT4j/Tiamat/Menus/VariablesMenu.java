package Menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.mt4j.MTApplication;
import org.mt4j.components.TransformSpace;
import org.mt4j.components.interfaces.IMTComponent3D;
import org.mt4j.components.visibleComponents.font.FontManager;
import org.mt4j.components.visibleComponents.font.IFont;
import org.mt4j.components.visibleComponents.shapes.MTRoundRectangle;
import org.mt4j.components.visibleComponents.shapes.MTRectangle.PositionAnchor;
import org.mt4j.components.visibleComponents.widgets.MTList;
import org.mt4j.components.visibleComponents.widgets.MTListCell;
import org.mt4j.components.visibleComponents.widgets.MTTextArea;
import org.mt4j.components.visibleComponents.widgets.buttons.MTImageButton;
import org.mt4j.input.inputProcessors.IGestureEventListener;
import org.mt4j.input.inputProcessors.MTGestureEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapProcessor;
import org.mt4j.util.MT4jSettings;
import org.mt4j.util.MTColor;
import org.mt4j.util.animation.Animation;
import org.mt4j.util.animation.AnimationEvent;
import org.mt4j.util.animation.IAnimationListener;
import org.mt4j.util.animation.MultiPurposeInterpolator;
import org.mt4j.util.math.Vector3D;

import processing.core.PImage;

import basic.scenes.Scene3;

import Tiamat.StartTiamat;

public class VariablesMenu extends Menu{
	public VariablesMenu(MTApplication mtApplication, String name) {
		super(mtApplication, name);
	}
	
	private MTListCell createListCell(final MTApplication mtApplication, final String label, final AST.Node node, IFont font, float cellWidth, float cellHeight, final MTColor cellFillColor, final MTColor cellPressedFillColor){
		makeCell(mtApplication, cellWidth, cellHeight, cellFillColor);
		makeListLabel(mtApplication, font, label);
		cell.addChild(listLabel);
		listLabel.setPositionRelativeToParent(cell.getCenterPointLocal());
		cell.unregisterAllInputProcessors();
		cell.registerInputProcessor(new TapProcessor(mtApplication, 15));
		cell.addGestureListener(TapProcessor.class, new IGestureEventListener() {
			public boolean processGestureEvent(MTGestureEvent ge) {
				TapEvent te = (TapEvent)ge;
				switch (te.getTapID()) { 
				case TapEvent.BUTTON_DOWN:
					cell.setFillColor(cellPressedFillColor);
					break;
				case TapEvent.BUTTON_UP:
					cell.setFillColor(cellFillColor);
					break;
				case TapEvent.BUTTON_CLICKED:
					System.out.println("Button clicked: " + label);
					cell.setFillColor(cellFillColor);
					if (StartTiamat.selected == null){
						System.out.println("There is no node selected");
					}else{
						AST.Node parent = StartTiamat.selected.getParent();
						parent.setChild(StartTiamat.selected, node);
						node.setParent(parent);
						StartTiamat.selected = null;
						Tiamat.Tiamat.redraw();
					}
				}
				return false;
			}
		});
		return cell;
	}
			
		public void Make(MTApplication mtApplication, String name, Boolean out){
			doSlideIn = out;
			IFont font = FontManager.getInstance().createFont(mtApplication, "SansSerif.Bold", 15, MTColor.WHITE, MTColor.WHITE);
			makeMapMenu(mtApplication, out);
			mapMenu.addChild(returnButton(mtApplication));
			StartTiamat.general.addChild(mapMenu);
			System.out.println("added menu");
			makeList(mtApplication);
			list.setPositionRelativeToParent(mapMenu.getCenterPointLocal());
			mapMenu.addChild(list);
			for(int i=0; i< StartTiamat.variables.size(); i++){
				Tiamat.Templates template = StartTiamat.variables.get(i);
				list.addListElement(this.createListCell(mtApplication, template.getName(), template.getFunction(), font,  cellWidth, cellHeight, cellFillColor, cellPressedFillColor));
			};	
			
			MultiPurposeInterpolator in = new MultiPurposeInterpolator(0,170, 700, 0.1f, 0.7f, 1);
			
			final Animation slideOut = new Animation("slide out animation", in, mapMenu);
			slideOut.addAnimationListener(new IAnimationListener() {
				public void processAnimationEvent(AnimationEvent ae) {
					float delta = ae.getCurrentStepDelta();
					((IMTComponent3D)ae.getTargetObject()).translateGlobal(new Vector3D(delta,0,0));
					switch (ae.getId()) {
					case AnimationEvent.ANIMATION_ENDED:
						doSlideIn = true;
						animationRunning = false;
						break;
					}
				}
			});
			
			final Animation slideIn = new Animation("slide out animation", in, mapMenu);
			slideIn.addAnimationListener(new IAnimationListener() {
				public void processAnimationEvent(AnimationEvent ae) {
					float delta = -ae.getCurrentStepDelta();
					((IMTComponent3D)ae.getTargetObject()).translateGlobal(new Vector3D(delta,0,0));
					switch (ae.getId()) {
					case AnimationEvent.ANIMATION_ENDED:
						doSlideIn = false;
						animationRunning = false;
						break;
					}
				}
			});
			
			mapMenu.unregisterAllInputProcessors();
			mapMenu.registerInputProcessor(new TapProcessor(mtApplication, 50));
			mapMenu.addGestureListener(TapProcessor.class, new IGestureEventListener() {
				public boolean processGestureEvent(MTGestureEvent ge) {
					if (((TapEvent)ge).getTapID() == TapEvent.BUTTON_CLICKED){
						if (!animationRunning){
							animationRunning = true;
							if (doSlideIn){
								slideIn.start();
							}else{
								slideOut.start();
							}
						}
					}
					return false;
				}
			});

			};	

			//Button to get to the next scene
			public void addArrow(MTApplication mtApplication){
			PImage arrow = mtApplication.loadImage("arrowRight.png");
			MTImageButton nextSceneButton = new MTImageButton(arrow, mtApplication);
			nextSceneButton.setNoStroke(true);
			if (MT4jSettings.getInstance().isOpenGlMode())
				nextSceneButton.setUseDirectGL(true);
			nextSceneButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					switch (ae.getID()) {
					case TapEvent.BUTTON_CLICKED:
						//Save the current scene on the scene stack before changing
						System.out.println("Back clicked");
						break;
					default:
						break;
					}
				}
			});
			mapMenu.addChild(nextSceneButton);
			nextSceneButton.setPositionGlobal(new Vector3D(0,0));			
}
}
