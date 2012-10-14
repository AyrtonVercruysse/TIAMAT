package Menus;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.mt4j.MTApplication;
import org.mt4j.components.interfaces.IMTComponent3D;
import org.mt4j.components.visibleComponents.font.FontManager;
import org.mt4j.components.visibleComponents.font.IFont;
import org.mt4j.components.visibleComponents.shapes.MTRectangle;
import org.mt4j.components.visibleComponents.shapes.MTRoundRectangle;
import org.mt4j.components.visibleComponents.shapes.MTRectangle.PositionAnchor;
import org.mt4j.components.visibleComponents.widgets.MTList;
import org.mt4j.components.visibleComponents.widgets.MTListCell;
import org.mt4j.components.visibleComponents.widgets.MTTextArea;
import org.mt4j.components.visibleComponents.widgets.MTTextArea.ExpandDirection;
import org.mt4j.components.visibleComponents.widgets.buttons.MTImageButton;
import org.mt4j.components.visibleComponents.widgets.keyboard.MTKeyboard;
import org.mt4j.input.inputProcessors.IGestureEventListener;
import org.mt4j.input.inputProcessors.MTGestureEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapProcessor;
import org.mt4j.sceneManagement.AbstractScene;
import org.mt4j.util.MT4jSettings;
import org.mt4j.util.MTColor;
import org.mt4j.util.animation.Animation;
import org.mt4j.util.animation.AnimationEvent;
import org.mt4j.util.animation.IAnimationListener;
import org.mt4j.util.animation.MultiPurposeInterpolator;
import org.mt4j.util.math.Vector3D;

import processing.core.PImage;

import rendering.Begin;
import rendering.Function;
import rendering.RenderVisitor;
import rendering.Renderer;

import AST.Node;

import Tiamat.StartTiamat;

import com.modestmaps.providers.AbstractMapProvider;
import com.modestmaps.providers.BlueMarble;
import com.modestmaps.providers.CloudMade;
import com.modestmaps.providers.DailyPlanet;
import com.modestmaps.providers.Microsoft;
import com.modestmaps.providers.OpenStreetMaps;

public abstract class Menu extends AbstractScene {
	boolean animationRunning = false;
	boolean doSlideIn = false;
	MTRoundRectangle mapMenu;
	private String imagePath =  "Data" + MTApplication.separator;
	AST.Begin test = new AST.Begin(null);
	MTTextArea listLabel;
	MTListCell cell;
	MTList list;
	float cellWidth = 155;
	float cellHeight = 40;
	MTColor cellFillColor = new MTColor(new MTColor(0,0,0,210));
	MTColor cellPressedFillColor = new MTColor(new MTColor(20,20,20,220));
	
	public Menu(MTApplication mtApplication, String name) {
		super(mtApplication, name);
		MTRectangle test = new MTRectangle(0,0,10,10, mtApplication);
		test.setFillColor(new MTColor(255,0,0));
		getCanvas().addChild(test);
	}
	
	public void makeListLabel(MTApplication mtApplication, IFont font, String label){
		listLabel = new MTTextArea(mtApplication, font);
		listLabel.setNoFill(true);
		listLabel.setNoStroke(true);
		listLabel.setText(label);
	}
	
	public void makeCell(MTApplication mtApplication, float cellWidth, float cellHeight, MTColor cellFillColor ){
		cell = new MTListCell(cellWidth, cellHeight, mtApplication);
		cell.setChildClip(null); 
		cell.setFillColor(cellFillColor);
	}
	 public void makeList(MTApplication mtApplication){
		list = new MTList(0,0, 152, 4* cellHeight + 4*3, mtApplication);
		list.setChildClip(null);
		list.setNoFill(true);
		list.setNoStroke(true);
		list.unregisterAllInputProcessors();
		list.setAnchor(PositionAnchor.CENTER);
	}
	 public void makeMapMenu(MTApplication mtApplication, Boolean out){
		mapMenu	 = new MTRoundRectangle(0,0,0, 220,335, 20,20, mtApplication);
		mapMenu.setFillColor(new MTColor(35,35,35,180));
		mapMenu.setStrokeColor(new MTColor(35,35,35,180));
		mapMenu.setNoStroke(true);
		if(out){
			mapMenu.setPositionGlobal(new Vector3D(mtApplication.width/2f+170, mtApplication.height/2f));
			
		}else{
			mapMenu.setPositionGlobal(new Vector3D(mtApplication.width/2f, mtApplication.height/2f));
			System.out.println("Width" + mtApplication.width/2f);
		}
		
		mapMenu.translateGlobal(new Vector3D(-mtApplication.width/2f - 80,0));
	 }
		
	
	public MTImageButton returnButton(final MTApplication mtApplication){
		PImage arrow = mtApplication.loadImage(imagePath + "return_arrow2.jpg");
		MTImageButton nextSceneButton = new MTImageButton(arrow, mtApplication);
		nextSceneButton.setNoStroke(true);
		nextSceneButton.setNoFill(true);
		if (MT4jSettings.getInstance().isOpenGlMode())
			nextSceneButton.setUseDirectGL(true);
		nextSceneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				switch (ae.getID()) {
				case TapEvent.BUTTON_CLICKED:
					//Save the current scene on the scene stack before changing
					System.out.println("Back clicked");
					//doSlideIn = false;
					StartTiamat.general.removeChild(mapMenu);
					Tiamat.Tiamat.beginMenu.Make(mtApplication, "BeginMenu", true);
	
					break;
				default:
					break;
				}
			}
		});
		nextSceneButton.setAnchor(PositionAnchor.UPPER_LEFT);
		nextSceneButton.setPositionRelativeToParent(new Vector3D(200,20));		
		return nextSceneButton;
		//mapMenu.addChild(new MTRectangle(0,0,200,200,mtApplication));
		//mapMenu.addChild(nextSceneButton);
}
	
	abstract public void Make(MTApplication mtApplication, String name, Boolean out);
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void shutDown() {
		// TODO Auto-generated method stub
		
	}
}
