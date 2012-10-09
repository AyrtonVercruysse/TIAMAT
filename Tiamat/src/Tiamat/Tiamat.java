package Tiamat;

import org.mt4j.AbstractMTApplication;
import org.mt4j.components.visibleComponents.shapes.MTRectangle;
import org.mt4j.sceneManagement.AbstractScene;
import org.mt4j.util.math.Vector3D;
import rendering.RenderVisitor;
import rendering.Renderer;
import Menus.BeginMenu;
import Menus.FunctionsMenu;
import Menus.MyFunctionsMenu;
import Menus.OperationsMenu;
import Menus.VariablesMenu;

public class Tiamat extends AbstractScene{
	static BeginMenu beginMenu;
	static Renderer<AST.Begin> beginRenderer;
	static RenderVisitor visitor;
	static AST.Begin main = new AST.Begin(null);
	static AbstractMTApplication mtApplication;
	static String name;
	public static FunctionsMenu functionsMenu;
	public static MyFunctionsMenu myFunctionsMenu;
	public static OperationsMenu operationsMenu;
	public static VariablesMenu variablesMenu;
	public Tiamat(AbstractMTApplication mtApplication, String name){
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
		visitor = new RenderVisitor(mtApplication);
		redraw();
	}
	public static void redraw(){
		Vector3D pos = new Vector3D(0,0);
		StartTiamat.general.removeAllChildren();
		beginRenderer = visitor.visit(main);
		beginRenderer.display(StartTiamat.general, pos);
		beginMenu.Make(mtApplication, name);
	}
	public void makeTemplates(){
		//Functions
		StartTiamat.functions.add(IfThenElse());
		StartTiamat.functions.add(WhenDiscovered());
		StartTiamat.functions.add(WheneverDiscovered());
		StartTiamat.functions.add(WhileDo());
		//Operations
		StartTiamat.operations.add(Plus());
		StartTiamat.operations.add(Minus());
		StartTiamat.operations.add(Devide());
		StartTiamat.operations.add(Multiply());
	}
	
	//Functions
	public Templates IfThenElse(){
		String names[] = {"if:", "then:", "else:"};
		String contents[] = {"predicate", "consequent", "alternative"};
		AST.Node node = new AST.Function(null,names,contents);
		Templates dezen = new Templates("If:Then:Else", node);
		return dezen;
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
