package Menus;

import org.mt4j.MTApplication;
import org.mt4j.components.interfaces.IMTComponent3D;
import org.mt4j.components.visibleComponents.font.FontManager;
import org.mt4j.components.visibleComponents.font.IFont;
import org.mt4j.components.visibleComponents.shapes.MTRoundRectangle;
import org.mt4j.components.visibleComponents.shapes.MTRectangle.PositionAnchor;
import org.mt4j.components.visibleComponents.widgets.MTList;
import org.mt4j.components.visibleComponents.widgets.MTListCell;
import org.mt4j.components.visibleComponents.widgets.MTTextArea;
import org.mt4j.input.inputProcessors.IGestureEventListener;
import org.mt4j.input.inputProcessors.MTGestureEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapProcessor;
import org.mt4j.util.MTColor;
import org.mt4j.util.animation.Animation;
import org.mt4j.util.animation.AnimationEvent;
import org.mt4j.util.animation.IAnimationListener;
import org.mt4j.util.animation.MultiPurposeInterpolator;
import org.mt4j.util.math.Vector3D;

import Tiamat.StartTiamat;

public class BeginMenu extends Menu{
	
	public BeginMenu(MTApplication mtApplication, String name) {
		super(mtApplication, name);
	}
	
	private MTListCell createListCell(final MTApplication mtApplication, final String label, final Menu menu, IFont font, float cellWidth, float cellHeight, final MTColor cellFillColor, final MTColor cellPressedFillColor){
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
					StartTiamat.general.removeChild(mapMenu);
					System.out.println(menu);
					menu.Make(mtApplication, label, true);
					cell.setFillColor(cellFillColor);
				}
				return false;
			}
		});
		return cell;
	}
			
		public void Make(MTApplication mtApplication, String name, Boolean out){
			/// Create map provider menu \\\
			doSlideIn = out;
			IFont font = FontManager.getInstance().createFont(mtApplication, "SansSerif.Bold", 15, MTColor.WHITE, MTColor.WHITE);
			makeMapMenu(mtApplication, out);
			StartTiamat.general.addChild(mapMenu);
			System.out.println("added menu");
			makeList(mtApplication);
			list.setPositionRelativeToParent(mapMenu.getCenterPointLocal());
			mapMenu.addChild(list);
			list.addListElement(this.createListCell(mtApplication, "Functions", Tiamat.Tiamat.functionsMenu, font,  cellWidth, cellHeight, cellFillColor, cellPressedFillColor));
			list.addListElement(this.createListCell(mtApplication, "Operations", Tiamat.Tiamat.operationsMenu, font, cellWidth, cellHeight, cellFillColor, cellPressedFillColor));
			list.addListElement(this.createListCell(mtApplication, "Definitions", Tiamat.Tiamat.definitionsMenu, font, cellWidth, cellHeight, cellFillColor, cellPressedFillColor));
			
			list.addListElement(this.createListCell(mtApplication, "Variables", Tiamat.Tiamat.variablesMenu, font, cellWidth, cellHeight, cellFillColor, cellPressedFillColor));
			list.addListElement(this.createListCell(mtApplication, "My Functions", Tiamat.Tiamat.myFunctionsMenu, font, cellWidth, cellHeight, cellFillColor, cellPressedFillColor));
			
			
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

}
