package basic.helloWorld;
import org.mt4j.MTApplication;
import org.mt4j.components.TransformSpace;
import org.mt4j.components.visibleComponents.font.FontManager;
import org.mt4j.components.visibleComponents.font.IFont;
import org.mt4j.components.visibleComponents.shapes.MTRectangle;
import org.mt4j.components.visibleComponents.widgets.MTTextArea;
import org.mt4j.input.gestureAction.InertiaDragAction;
import org.mt4j.input.inputProcessors.componentProcessors.dragProcessor.DragProcessor;
import org.mt4j.input.inputProcessors.globalProcessors.CursorTracer;
import org.mt4j.sceneManagement.AbstractScene;
import org.mt4j.util.MTColor;
import org.mt4j.util.math.ToolsMath;
import org.mt4j.util.math.Vector3D;

public class HelloWorldScene extends AbstractScene {

	public HelloWorldScene(MTApplication mtApplication, String name) {
		super(mtApplication, name);
	    MTColor white = new MTColor(255,255,255);
		MTColor black = new MTColor(0,0,0);
	//	this.setClearColor(new MTColor(146, 150, 188, 255));
		//Show touches
		this.registerGlobalInputProcessor(new CursorTracer(mtApplication, this));

		IFont fontArial = FontManager.getInstance().createFont(mtApplication, "arial.ttf", 
				20, 	//Font size
				black,  //Font fill color
				black);	//Font outline color
		
		MTRectangle r = new MTRectangle(0,0,500,500,mtApplication);
		//r.setFillColor(new MTColor(ToolsMath.getRandom(50,255),ToolsMath.getRandom(50,255),ToolsMath.getRandom(50,255)));
		r.addGestureListener(DragProcessor.class, new InertiaDragAction());
		getCanvas().addChild(r);
	//	r.setPositionGlobal(new Vector3D(ToolsMath.getRandom(0, mtApplication.width), ToolsMath.getRandom(0, mtApplication.height)));
		//MTRectangle r2 = new MTRectangle(0,0,100,50,mtApplication);	
		//r2.setFillColor(new MTColor(ToolsMath.getRandom(50,255),ToolsMath.getRandom(50,255),ToolsMath.getRandom(50,255)));
		//r2.addGestureListener(DragProcessor.class, new InertiaDragAction());
		//r.addChild(r2);
		//Create a textfield
		MTTextArea textField = new MTTextArea(mtApplication, fontArial); 
		MTTextArea textField2;
		MTTextArea textField3;
		//textField.setNoStroke(true);
		textField.setNoFill(true);
		textField.setText("if:");
		//textField.setPositionRelativeToOther(textField2, new Vector3D(250,0));
		//blur2 = textField.getHeightXYVectLocal(); 
		textField.setBoundsBehaviour(5);
		//Center the textfield on the screen
		Vector3D blur = textField.getPosition(TransformSpace.RELATIVE_TO_PARENT);
		blur.setY(blur.getY() + 250);
		//Add the textfield to our canvas
		r.addChild(textField);
		//textField3.setPositionGlobal(blur);
		textField2 = new MTTextArea(mtApplication, fontArial); 
		textField2.setPositionGlobal(new Vector3D(8, 45));
		//textField2.setBoundsBehaviour(5);
		textField2.setNoFill(true);
		textField2.setText("then: ");
		textField2.setBoundsBehaviour(5);
		r.addChild(textField2);
		textField3 = new MTTextArea(mtApplication, fontArial); 
		textField3.setPositionGlobal(blur);
		//textField2.setBoundsBehaviour(5);
		textField3.setNoFill(true);
		textField3.setText("else: ");
		textField3.setBoundsBehaviour(5);
		r.addChild(textField3);
		MTRectangle r2 = new MTRectangle(50,0,350,30,mtApplication);
		r2.setFillColor(new MTColor(50,50,50));
		r2.addGestureListener(DragProcessor.class, new InertiaDragAction());
		r.addChild(r2);
		MTRectangle r3 = new MTRectangle(0,60,350,200,mtApplication);
		r3.setFillColor(new MTColor(50,50,50));
		r3.addGestureListener(DragProcessor.class, new InertiaDragAction());
		r.addChild(r3);
		MTRectangle r4 = new MTRectangle(0,300,350,200,mtApplication);
		r4.setFillColor(new MTColor(50,50,50));
		r4.addGestureListener(DragProcessor.class, new InertiaDragAction());
		r.addChild(r4);
	}
	
	@Override
	public void init() {}
	@Override
	public void shutDown() {}
}
