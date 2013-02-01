package Tiamat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.mt4j.MTApplication;
import org.mt4j.components.visibleComponents.font.FontManager;
import org.mt4j.components.visibleComponents.shapes.MTRectangle;
import org.mt4j.components.visibleComponents.shapes.MTRectangle.PositionAnchor;
import org.mt4j.components.visibleComponents.widgets.MTTextArea;
import org.mt4j.components.visibleComponents.widgets.MTTextArea.ExpandDirection;
import org.mt4j.components.visibleComponents.widgets.buttons.MTImageButton;
import org.mt4j.components.visibleComponents.widgets.keyboard.MTKeyboard;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapEvent;
import org.mt4j.sceneManagement.AbstractScene;
import org.mt4j.sceneManagement.Iscene;
import org.mt4j.util.MT4jSettings;
import org.mt4j.util.MTColor;
import org.mt4j.util.math.Vector3D;

import processing.core.PImage;
import rendering.RenderVisitor;
import rendering.Renderer;
import Menus.BeginMenu;
import Menus.DefinitionsMenu;
import Menus.FunctionsMenu;
import Menus.MyFunctionsMenu;
import Menus.OperationsMenu;
import Menus.VariablesMenu;

public class Tiamat extends AbstractScene{
	private static String imagePath =  "Data" + MTApplication.separator;
	public static BeginMenu beginMenu;
	static Renderer<AST.Begin> beginRenderer;
	static RenderVisitor visitor;
	static AST.Begin main = new AST.Begin(null);
	static MTApplication mtApplication;
	static String name;
	public static FunctionsMenu functionsMenu;
	public static MyFunctionsMenu myFunctionsMenu;
	public static OperationsMenu operationsMenu;
	public static VariablesMenu variablesMenu;
	public static DefinitionsMenu definitionsMenu;
	public static MTTextArea t;
	public static MTKeyboard keyb;
	public static MTImageButton submitButton;
	public Tiamat(MTApplication mtApplication, String name){
		super(mtApplication, name);
		makeTemplates();
		this.mtApplication = mtApplication;
		this.name = name;
		StartTiamat.general = new MTRectangle(0,0,1024,768,mtApplication);
		StartTiamat.general.setPickable(false);
		getCanvas().addChild(StartTiamat.general);
		beginMenu = new BeginMenu(mtApplication, name);
		functionsMenu = new FunctionsMenu(mtApplication, name);
		myFunctionsMenu = new MyFunctionsMenu(mtApplication, name);
		operationsMenu = new OperationsMenu(mtApplication, name);
		variablesMenu = new VariablesMenu(mtApplication, name);
		definitionsMenu = new DefinitionsMenu(mtApplication, name);
		visitor = new RenderVisitor(mtApplication);
		System.out.println("made");
		AST.Placeholder placeholder = new AST.Placeholder(null, "test",false);
		System.out.println(placeholder.getName());
		/*try {
			placeholder.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Tis kapot");
		}*/
		redraw();
		System.out.println("picked:");
		System.out.println(StartTiamat.general.pick(25,25));
	}
	
	public static MTImageButton runButton(MTApplication mtApplication){
		PImage arrow = mtApplication.loadImage(imagePath + "run.png");
		MTImageButton nextSceneButton = new MTImageButton(arrow, mtApplication);
		nextSceneButton.setNoStroke(true);
		if (MT4jSettings.getInstance().isOpenGlMode())
			nextSceneButton.setUseDirectGL(true);
		nextSceneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				switch (ae.getID()) {
				case TapEvent.BUTTON_CLICKED:
					//Save the current scene on the scene stack before changing
					System.out.println("run clicked");
					System.out.println(main.toString());

					break;
				default:
					break;
				}
			}
		});
		nextSceneButton.setAnchor(PositionAnchor.UPPER_LEFT);
		nextSceneButton.setPositionRelativeToParent(new Vector3D(875,700));		
		return nextSceneButton;
		//mapMenu.addChild(new MTRectangle(0,0,200,200,mtApplication));
		//mapMenu.addChild(nextSceneButton);
}
	public static void submitButton(MTApplication mtApplication){
		PImage submit = mtApplication.loadImage(imagePath + "submit.jpg");
		submitButton = new MTImageButton(submit, mtApplication);
		submitButton.setNoStroke(true);
		if (MT4jSettings.getInstance().isOpenGlMode())
			submitButton.setUseDirectGL(true);
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				switch (ae.getID()) {
				case TapEvent.BUTTON_CLICKED:
					//Save the current scene on the scene stack before changing
					System.out.println(t.getText());
					StartTiamat.general.removeChild(keyb);
					StartTiamat.general.removeChild(submitButton);
					DefinitionsMenu.cont();
					break;
				default:
					break;
				}
			}
		});
		submitButton.setAnchor(PositionAnchor.UPPER_LEFT);
		submitButton.setPositionRelativeToParent(new Vector3D(900,400));		
}
	
	public static void keyboard(){
		keyb = new MTKeyboard(mtApplication);
	    keyb.setFillColor(new MTColor(30, 30, 30, 210));
	    keyb.setStrokeColor(new MTColor(0,0,0,255));
	    
	    t = new MTTextArea(mtApplication, FontManager.getInstance().createFont(mtApplication, "arial.ttf", 50, 
	    		new MTColor(0,0,0,255), //Fill color 
				new MTColor(0,0,0,255))); //Stroke color
	    t.setExpandDirection(ExpandDirection.UP);
		t.setStrokeColor(new MTColor(0,0 , 0, 255));
		t.setFillColor(new MTColor(205,200,177, 255));
		t.unregisterAllInputProcessors();
		t.setEnableCaret(true);
		t.snapToKeyboard(keyb);
		keyb.addTextInputListener(t);
		keyb.setPositionRelativeToParent(new Vector3D(500,300));
		
		}
	
	
	public static void redraw(){
		Vector3D pos = new Vector3D(0,0);
		StartTiamat.general.removeAllChildren();
		beginRenderer = visitor.visit(main);
		beginRenderer.display(StartTiamat.general, pos);
		beginMenu.Make(mtApplication, name, false);
		StartTiamat.general.addChild(runButton(mtApplication));
	}
	public void makeTemplates(){
		//Functions
		StartTiamat.functions.add(IfThenElse());
		StartTiamat.functions.add(WhenDiscovered());
		StartTiamat.functions.add(WheneverDiscovered());
		StartTiamat.functions.add(WhileDo());
		//Definitions
		StartTiamat.definitions.add(Value());
		StartTiamat.definitions.add(Definition());
		StartTiamat.definitions.add(FunctionDefinition());
		StartTiamat.definitions.add(TableDefinition());

		//Operations
		StartTiamat.operations.add(Plus());
		StartTiamat.operations.add(Minus());
		StartTiamat.operations.add(Devide());
		StartTiamat.operations.add(Multiply());
		StartTiamat.operations.add(Plus());
		StartTiamat.operations.add(Plus());
		StartTiamat.operations.add(Plus());
		StartTiamat.operations.add(Plus());
		StartTiamat.operations.add(Plus());
		StartTiamat.operations.add(Plus());
		StartTiamat.operations.add(Plus());
		StartTiamat.operations.add(Plus());
		StartTiamat.operations.add(Plus());
		StartTiamat.operations.add(Plus());
		StartTiamat.operations.add(Plus());
		StartTiamat.operations.add(Plus());
		StartTiamat.operations.add(Plus());
		StartTiamat.operations.add(Plus());
		StartTiamat.operations.add(Plus());
		StartTiamat.operations.add(Plus());
		StartTiamat.operations.add(Plus());
		StartTiamat.operations.add(Plus());
		StartTiamat.operations.add(Plus());
		StartTiamat.operations.add(Plus());
		StartTiamat.operations.add(Plus());
		StartTiamat.operations.add(Plus());
		StartTiamat.operations.add(Plus());
		StartTiamat.operations.add(Plus());
		StartTiamat.operations.add(Plus());
		StartTiamat.operations.add(Plus());
		StartTiamat.operations.add(Plus());
	}
	
	//Definitions
	public Templates Value(){
		AST.Node node = new AST.Value(null, "Empty");
		return new Templates("Value", node);
	}
	public Templates Definition(){
		AST.Node node = new AST.Definition(null);
		return new Templates("Definition", node);
	}
	public Templates FunctionDefinition(){
		AST.Node node = new AST.FunctionDefinition(null, 0);
		return new Templates("Function", node);
	}
	public Templates TableDefinition(){
		AST.Node node = new AST.TableDefinition(null);
		return new Templates("Table", node);
	}
	
	//Functions
	public Templates IfThenElse(){
		String names[] = {"if:", "then:", "else:"};
		String contents[] = {"predicate", "consequent", "alternative"};
		AST.Node node = new AST.Function(null,names,contents);
		return new Templates("If:Then:Else", node);
	}
	public Templates WhenDiscovered(){
		String names[] = {"when:", "discovered:"};
		String contents[] = {"predicate", "content"};
		AST.Node node = new AST.Function(null, names, contents);
		return new Templates("When:Discovered", node);
	}
	public Templates WheneverDiscovered(){
		String names[] = {"whenever:", "discovered:"};
		String contents[] = {"predicate", "content"};
		AST.Node node = new AST.Function(null, names, contents);
		return new Templates("Whenever:Discovered", node);
	}
	public Templates WhileDo(){
		String names[] = {"while:", "do:"};
		String contents[] = {"predicate", "content"};
		AST.Node node = new AST.Function(null, names, contents);
		return new Templates("While:Do", node);
	}
	//Operations
	public Templates Plus(){
		AST.Node node = new AST.Operation(null, "+");
		return new Templates("+", node);
	}
	public Templates Minus(){
		AST.Node node = new AST.Operation(null, "-");
		return new Templates("-", node);
	}
	public Templates Devide(){
		AST.Node node = new AST.Operation(null, "/");
		return new Templates("/", node);
	}
	public Templates Multiply(){
		AST.Node node = new AST.Operation(null, "*");
		return new Templates("*", node);
	}
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void shutDown() {
		// TODO Auto-generated method stub
		
	}
}
