package Menus;

import org.mt4j.MTAndroidApplication;
import org.mt4j.components.visibleComponents.shapes.MTRectangle;
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
import org.mt4j.sceneManagement.AbstractScene;
import org.mt4j.util.MT4jSettings;
import org.mt4j.util.MTColor;
import org.mt4j.util.font.IFont;
import org.mt4j.util.math.Vector3D;
import processing.core.PImage;
import vub.tiamat.StartTiamat;
/**
 * The implementation of the menu class.
 * @author Ayrton Vercruysse
 *
 */
public abstract class Menu extends AbstractScene {
	boolean animationRunning = false;
	boolean doSlideIn = false;
	MTRoundRectangle mapMenu;
	private static String imagePath =  "";
	AST.Begin test = new AST.Begin(null);
	MTTextArea listLabel;
	MTListCell cell;
	MTList list;
	float cellWidth = 150;
	float cellHeight = 60;
	MTColor cellFillColor = new MTColor(new MTColor(0,0,0,210));
	MTColor cellPressedFillColor = new MTColor(new MTColor(20,20,20,220));
	
	
	public Menu(MTAndroidApplication mtApplication, String name) {
		super(mtApplication, name);
		MTRectangle test = new MTRectangle(mtApplication, 0,0,10,10);
		test.setFillColor(new MTColor(255,0,0));
		getCanvas().addChild(test);
	}
	
	public void makeListLabel(MTAndroidApplication mtApplication, IFont font, String label){
		listLabel = new MTTextArea(mtApplication, font);
		listLabel.setNoFill(true);
		listLabel.setNoStroke(true);
		listLabel.setText(label);
	}
	
	public void makeCell(MTAndroidApplication mtApplication, float cellWidth, float cellHeight, MTColor cellFillColor ){
		cell = new MTListCell(mtApplication, cellWidth, cellHeight);
		cell.setChildClip(null); 
		cell.setFillColor(cellFillColor);
	}
	 public void makeList(MTAndroidApplication mtApplication){
		list = new MTList(mtApplication, 0,0, 152, 4* cellHeight + 4*3);
		list.setChildClip(null);
		list.setNoFill(true);
		list.setNoStroke(true);
		list.unregisterAllInputProcessors();
		list.setAnchor(PositionAnchor.CENTER);
	}
	 public void makeMapMenu(MTAndroidApplication mtApplication, Boolean out){
		mapMenu	 = new MTRoundRectangle(mtApplication, 0,0,0, 250,1140, 20,20);
		mapMenu.setFillColor(new MTColor(35,35,35,180));
		mapMenu.setStrokeColor(new MTColor(35,35,35,180));
		if(out){
			mapMenu.setPositionGlobal(new Vector3D(mtApplication.width/2f+170, mtApplication.height/2f));
			
		}else{
			mapMenu.setPositionGlobal(new Vector3D(mtApplication.width/2f, mtApplication.height/2f));
			System.out.println("Width" + mtApplication.width/2f);
		}
		
		mapMenu.translateGlobal(new Vector3D(-mtApplication.width/2f - 80,0));
	 }
		
	
	public MTImageButton returnButton(final MTAndroidApplication mtApplication){
		PImage arrow = mtApplication.loadImage(imagePath + "Back.jpg");
		MTImageButton nextSceneButton = new MTImageButton(mtApplication, arrow);
		nextSceneButton.setNoStroke(true);
		if (MT4jSettings.getInstance().isOpenGlMode())
			nextSceneButton.setUseDirectGL(true);
		nextSceneButton.addGestureListener(TapProcessor.class, new IGestureEventListener() {
			public boolean processGestureEvent(MTGestureEvent ge) {
				TapEvent te = (TapEvent)ge;
				if (te.isTapped()){
					//Save the current scene on the scene stack before changing
					System.out.println("Back clicked");
					//doSlideIn = false;
					StartTiamat.general.removeChild(mapMenu);
					vub.tiamat.Tiamat.beginMenu.Make(mtApplication, "BeginMenu", true);
				}
				return true;
			}
		});
		nextSceneButton.setAnchor(PositionAnchor.UPPER_LEFT);
		nextSceneButton.setPositionRelativeToParent(new Vector3D(150,20));		
		return nextSceneButton;
		//mapMenu.addChild(new MTRectangle(0,0,200,200,mtApplication));
		//mapMenu.addChild(nextSceneButton);
}
	

	
	
	abstract public void Make(MTAndroidApplication mtApplication, String name, Boolean out);
	@Override
	public void init() {
	}
	@Override
	public void shutDown() {
	}
}
