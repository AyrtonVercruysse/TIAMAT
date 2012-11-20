package vub.tiamat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;
import org.mt4j.MTAndroidApplication;
import org.mt4j.components.visibleComponents.shapes.MTRectangle;
import org.mt4j.components.visibleComponents.shapes.MTRectangle.PositionAnchor;
import org.mt4j.components.visibleComponents.widgets.buttons.MTImageButton;
import org.mt4j.input.inputProcessors.IGestureEventListener;
import org.mt4j.input.inputProcessors.MTGestureEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapProcessor;
import org.mt4j.util.MT4jSettings;
import org.mt4j.util.math.Vector3D;

import processing.core.PImage;
import vub.ast.Node;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
/**
 * This class starts Tiamat.
 * @author Ayrton Vercruysse
 *
 */
public class StartTiamat extends MTAndroidApplication {
	public static Node selected;		// The currently selected node.
	public static Node menuNode;
	public static MTRectangle general; 	// The backbone rectangle.
	public static Vector<Templates> operations = new Vector<Templates>();
	public static Vector<Templates> functions = new Vector<Templates>();
	public static Vector<Templates> myFunctions = new Vector<Templates>();
	public static Vector<Templates> variables = new Vector<Templates>();
	public static Vector<Templates> definitions = new Vector<Templates>();

	@Override
	public void startUp() {
		
		addScene(new Tiamat(this, "vub.tiamat"));
		
	}
	

	

}
