package Menus;

import org.mt4j.AbstractMTApplication;
import org.mt4j.components.interfaces.IMTComponent3D;
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
import org.mt4j.util.font.FontManager;
import org.mt4j.util.font.IFont;
import org.mt4j.util.math.Vector3D;

import Tiamat.StartTiamat;

public class MyFunctionsMenu extends Menu{

	public MyFunctionsMenu(AbstractMTApplication mtApplication, String name) {
		super(mtApplication, name);
		//Menu(mtApplication, name);
		// TODO Auto-generated constructor stub
	}
	
	private MTListCell createListCell(final AbstractMTApplication mtApplication, final String label, IFont font, float cellWidth, float cellHeight, final MTColor cellFillColor, final MTColor cellPressedFillColor){
		final MTListCell cell = new MTListCell(cellWidth, cellHeight, mtApplication);
		cell.setChildClip(null); 
		cell.setFillColor(cellFillColor);
		MTTextArea listLabel = new MTTextArea(mtApplication, font);
		listLabel.setNoFill(true);
		listLabel.setNoStroke(true);
		listLabel.setText(label);
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
					    //StartTiamat.selected = node;
					    //node.display(general, new Vector3D(0,0));
					}else{
						System.out.println("Niet de main");
						System.out.println(StartTiamat.selected);
						//StartTiamat.selected.unselect();
						//StartTiamat.selected = node;
						//main.display(general, new Vector3D(0,0));
						System.out.println(StartTiamat.selected);
						//node.display(general, new Vector3D(0,0));
					}
										
					//map.setMapProvider(mapProvider);
					//break;
				}
				return false;
			}
		});
		return cell;
	}
			
		public void Make(AbstractMTApplication mtApplication, String name){
			/// Create map provider menu \\\
			IFont font = FontManager.getInstance().createFont(mtApplication, "SansSerif.Bold", 15, MTColor.WHITE, MTColor.WHITE);
			MTRoundRectangle mapMenu = new MTRoundRectangle(0,0,0, 220,335, 20,20, mtApplication);
			mapMenu.setFillColor(new MTColor(35,35,35,180));
			mapMenu.setStrokeColor(new MTColor(35,35,35,180));
			mapMenu.setPositionGlobal(new Vector3D(mtApplication.width/2f, mtApplication.height/2f));
			mapMenu.translateGlobal(new Vector3D(-mtApplication.width/2f - 80,0));
			getCanvas().addChild(mapMenu);
			System.out.println("added menu");
			float cellWidth = 155;
			float cellHeight = 40;
			MTColor cellFillColor = new MTColor(new MTColor(0,0,0,210));
			MTColor cellPressedFillColor = new MTColor(new MTColor(20,20,20,220));
			MTList list = new MTList(0,0, 152, 4* cellHeight + 4*3, mtApplication);
			list.setChildClip(null); //FIXME TEST -> do no clipping for performance
			list.setNoFill(true);
			list.setNoStroke(true);
			list.unregisterAllInputProcessors();
			list.setAnchor(PositionAnchor.CENTER);
			list.setPositionRelativeToParent(mapMenu.getCenterPointLocal());
			mapMenu.addChild(list);
			list.addListElement(this.createListCell(mtApplication, "vreemd", font,  cellWidth, cellHeight, cellFillColor, cellPressedFillColor));
			list.addListElement(this.createListCell(mtApplication, "vreemds", font, cellWidth, cellHeight, cellFillColor, cellPressedFillColor));
			list.addListElement(this.createListCell(mtApplication, "My Funns", font, cellWidth, cellHeight, cellFillColor, cellPressedFillColor));
			list.addListElement(this.createListCell(mtApplication, "Varles", font, cellWidth, cellHeight, cellFillColor, cellPressedFillColor));
			//list.addListElement(this.createListCell(mtApplication, "test4", font, new MTRectangle(0,0,500,500,mtApplication), cellWidth, cellHeight, cellFillColor, cellPressedFillColor));
			//list.addListElement(this.createListCell(mtApplication, "test5", font, new MTRectangle(0,0,500,500,mtApplication), cellWidth, cellHeight, cellFillColor, cellPressedFillColor));
			//list.addListElement(this.createListCell(mtApplication, "test6", font, new MTRectangle(0,0,500,500,mtApplication), cellWidth, cellHeight, cellFillColor, cellPressedFillColor));
			
			
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
