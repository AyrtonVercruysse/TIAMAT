package Menus;

import org.mt4j.MTApplication;
import org.mt4j.components.interfaces.IMTComponent3D;
import org.mt4j.components.visibleComponents.font.FontManager;
import org.mt4j.components.visibleComponents.font.IFont;
import org.mt4j.components.visibleComponents.widgets.MTListCell;
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

import rendering.FunctionDefinition;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;

import AST.Node;
import AST.TableDefinition;
import Tiamat.StartTiamat;
import Tiamat.Templates;

public class DefinitionsMenu extends Menu{
	static AST.Node thisNode;
	static String cont;
	public DefinitionsMenu(MTApplication mtApplication, String name) {
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
						Tiamat.Tiamat.keyboard();
						Tiamat.Tiamat.submitButton(mtApplication);
						if(label == "Save selected"){
							StartTiamat.general.addChild(Tiamat.Tiamat.keyb);
							StartTiamat.general.addChild(Tiamat.Tiamat.submitButton);
							thisNode = node;
							cont = "selected";
							
						}else{
						if(node instanceof AST.Value){
							StartTiamat.general.addChild(Tiamat.Tiamat.keyb);
							StartTiamat.general.addChild(Tiamat.Tiamat.submitButton);
							thisNode = node;
							cont = "value";
								                             
						}else if(node instanceof AST.FunctionDefinition){
							StartTiamat.general.addChild(Tiamat.Tiamat.keyb);
							StartTiamat.general.addChild(Tiamat.Tiamat.submitButton);
							thisNode = node;
							cont = "function1";
						}else if(node instanceof AST.TableDefinition){
							StartTiamat.general.addChild(Tiamat.Tiamat.keyb);
							StartTiamat.general.addChild(Tiamat.Tiamat.submitButton);
							thisNode = node;
							cont = "table1";
							
						}else if(node instanceof AST.Definition){
							StartTiamat.general.addChild(Tiamat.Tiamat.keyb);
							StartTiamat.general.addChild(Tiamat.Tiamat.submitButton);
							thisNode = node;
							cont = "definition";
							
						}
					}
				}}
				return false;
			}
		});
		return cell;
	}
	
		public static void cont(){
			System.out.println("cont called");
			if(cont == "selected"){
				Templates template = new Templates(Tiamat.Tiamat.t.getText(), thisNode);
				Tiamat.StartTiamat.myFunctions.add(template);
				StartTiamat.selected = null;
				Tiamat.Tiamat.redraw();
				
			}
			if(cont == "value"){
				((AST.Value) thisNode).setName(Tiamat.Tiamat.t.getText());
				AST.Node parent = StartTiamat.selected.getParent();
				parent.setChild(StartTiamat.selected, thisNode);
				thisNode.setParent(parent);
				StartTiamat.selected = null;
				Tiamat.Tiamat.redraw();
			}
			if(cont == "function1"){
				AST.Value name = new AST.Value(thisNode, Tiamat.Tiamat.t.getText());
				((AST.FunctionDefinition)thisNode).setName(name);
				StartTiamat.general.addChild(Tiamat.Tiamat.keyb);
				StartTiamat.general.addChild(Tiamat.Tiamat.submitButton);
				cont = "function2";				
			}else {
			if(cont == "function2"){
				System.out.println("function2");
				((AST.FunctionDefinition)thisNode).setArgumentList(Integer.parseInt(Tiamat.Tiamat.t.getText()));
				AST.Node parent = StartTiamat.selected.getParent();
				parent.setChild(StartTiamat.selected, thisNode);
				thisNode.setParent(parent);
				AST.FunctionCall call = new AST.FunctionCall(null, ((AST.FunctionDefinition)thisNode));
				Templates template = new Templates(call.getName(), call);
				Tiamat.StartTiamat.myFunctions.add(template);
				StartTiamat.selected = null;
				cont = null;
				Tiamat.Tiamat.redraw();
			}}
			if(cont == "table1"){
				AST.Value name = new AST.Value(thisNode,Tiamat.Tiamat.t.getText());
				((AST.TableDefinition)thisNode).setName(name);
				StartTiamat.general.addChild(Tiamat.Tiamat.keyb);
				StartTiamat.general.addChild(Tiamat.Tiamat.submitButton);
				cont = "table2";
				
			}else{
			if(cont == "table2"){
				AST.Value numberOfElements = new AST.Value(thisNode,Tiamat.Tiamat.t.getText());
				((AST.TableDefinition)thisNode).setNumberOfElements(numberOfElements);
				AST.Node parent = StartTiamat.selected.getParent();
				parent.setChild(StartTiamat.selected, thisNode);
				thisNode.setParent(parent);
				AST.TableCall call = new AST.TableCall(null, ((AST.TableDefinition)thisNode));
				Templates template = new Templates(call.getName(), call);
				Tiamat.StartTiamat.myFunctions.add(template);
				StartTiamat.selected = null;
				Tiamat.Tiamat.redraw();

			}}
			if(cont == "definition"){
				AST.Value name = new AST.Value(thisNode,Tiamat.Tiamat.t.getText());
				((AST.Definition)thisNode).setName(name);
				AST.Node parent = StartTiamat.selected.getParent();
				parent.setChild(StartTiamat.selected, thisNode);
				thisNode.setParent(parent);
				AST.Value call = new AST.Value(null, name.getName());
				Templates template = new Templates(call.getName(), call);
				Tiamat.StartTiamat.variables.add(template);
				StartTiamat.selected = null;
				Tiamat.Tiamat.redraw();
			}
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
			list.addListElement(this.createListCell(mtApplication, "Save selection", StartTiamat.selected, font,  cellWidth, cellHeight, cellFillColor, cellPressedFillColor));
			for(int i=0; i< StartTiamat.definitions.size(); i++){
				Tiamat.Templates template = StartTiamat.definitions.get(i);
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

}
