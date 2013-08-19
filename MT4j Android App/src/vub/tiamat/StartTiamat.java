package vub.tiamat;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;
import org.mt4j.MTAndroidApplication;
import org.mt4j.components.MTComponent;
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
import vub.rendering.Renderer;
import vub.templates.Templates;
import vub.tiamat.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Environment;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

/**
 * This class starts Tiamat.
 * 
 * @author Ayrton Vercruysse
 * 
 */
public class StartTiamat extends MTAndroidApplication {
	public static Node selected; // The currently selected node.
	public static Node menuNode;
	public static MTRectangle general; // The backbone rectangle.
	public static HashMap<MTComponent, Renderer<?>> mapping = new HashMap<MTComponent, Renderer<?>>();
	public static Vector<Templates> operations = new Vector<Templates>();
	public static Vector<Templates> functions = new Vector<Templates>();
	public static Vector<Templates> myFunctions = new Vector<Templates>();
	public static Vector<Templates> variables = new Vector<Templates>();
	public static Vector<Templates> definitions = new Vector<Templates>();
	static AssetManager assetManager;
	public static BufferedWriter out;
	public static BufferedWriter at;
	static BufferedWriter templateBuffered;


	/*public static void reOpen() {
		boolean mExternalStorageAvailable = false;
		boolean mExternalStorageWriteable = false;
		String state = Environment.getExternalStorageState();

		if (Environment.MEDIA_MOUNTED.equals(state)) {
			// We can read and write the media
			mExternalStorageAvailable = mExternalStorageWriteable = true;
			File root = Environment.getExternalStorageDirectory();
			File file = new File(root, "sourcecode.txt");
			try {
				if (root.canWrite()) {
					FileWriter filewriter = new FileWriter(file);
					out = new BufferedWriter(filewriter);
				}
			} catch (IOException e) {
				// Log.e("TAG", "Could not write file " + e.getMessage());
			}

		} else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
			// We can only read the media
			mExternalStorageAvailable = true;
			mExternalStorageWriteable = false;
		} else {
			// Something else is wrong. It may be one of many other states, but
			// all we need
			// to know is we can neither read nor write
			mExternalStorageAvailable = mExternalStorageWriteable = false;

		}

	}*/

	@Override
	public void startUp() {
		assetManager = getAssets();
		addScene(new Tiamat(this, "vub.tiamat", assetManager));
		boolean mExternalStorageAvailable = false;
		boolean mExternalStorageWriteable = false;
		String state = Environment.getExternalStorageState();

		if (Environment.MEDIA_MOUNTED.equals(state)) {
			// We can read and write the media
			mExternalStorageAvailable = mExternalStorageWriteable = true;
			System.out.println("Goowd");
			File root = Environment.getExternalStorageDirectory();
			//File file = new File(root, "save.xml");
			File templates = new File(root, "templates.xml");
			File ambienttalk = new File(root, "ambienttalk.txt");
			try {
				if (root.canWrite()) {
					//FileWriter filewriter = new FileWriter(file);
					FileWriter filewriter2 = new FileWriter(templates);
					FileWriter filewriter3 = new FileWriter(ambienttalk);
					//out = new BufferedWriter(filewriter);
					templateBuffered = new BufferedWriter(filewriter2);
					at = new BufferedWriter(filewriter3);
				}
			} catch (IOException e) {
				// Log.e("TAG", "Could not write file " + e.getMessage());
			}

		} else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
			// We can only read the media
			mExternalStorageAvailable = true;
			mExternalStorageWriteable = false;
		} else {
			// Something else is wrong. It may be one of many other states, but
			// all we need
			// to know is we can neither read nor write
			mExternalStorageAvailable = mExternalStorageWriteable = false;

		}

	}

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.game_menu, menu);
	    return true;
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.new_game:
	            //newGame();
	            return true;
	        case R.id.help:
	            //showHelp();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}*/
	
}
