package rendering;

import org.mt4j.MTAndroidApplication;
import org.mt4j.components.visibleComponents.shapes.MTRoundRectangle;
import org.mt4j.components.visibleComponents.shapes.MTRectangle.PositionAnchor;
import org.mt4j.components.visibleComponents.widgets.MTTextArea;
import org.mt4j.components.visibleComponents.widgets.buttons.MTImageButton;
import org.mt4j.input.inputProcessors.IGestureEventListener;
import org.mt4j.input.inputProcessors.MTGestureEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapProcessor;
import org.mt4j.util.MT4jSettings;
import org.mt4j.util.MTColor;
import org.mt4j.util.math.Vector3D;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.text.Editable;
import android.widget.EditText;

import processing.core.PImage;

import vub.tiamat.StartTiamat;
import vub.tiamat.Tiamat;
import vub.tiamat.voiceRecording;

public class Comments{
	private static String imagePath = "";
	static MTRoundRectangle rectangle;
	static String comments;
	static MTAndroidApplication mtApplication;
	AST.Node parent;
	static boolean inUse = false; 
	private static String ActionText;
	static String ActionReply;
	private static final String LOG_TAG = "AudioRecordTest";
    private static String mFileName = null;
    private static MediaRecorder mRecorder = null;
    private static MediaPlayer   mPlayer = null;
    static boolean recording = false;
    static boolean playing = false;
 
    static voiceRecording recorder = new voiceRecording();
    
	public Comments(MTAndroidApplication mtApplication, AST.Node parent) {
		this.mtApplication = mtApplication;

		System.out.println("comments called");
		this.parent = parent;
		rectangle = new MTRoundRectangle(mtApplication, 0, 0, 0, 500, 750, 20,
				20);
		rectangle.setFillColor(new MTColor(35, 35, 35, 180));
		rectangle.setStrokeColor(new MTColor(35, 35, 35, 180));


	}

	public static void show() {
		inUse = true;
		if (comments == null){
			ActionText = "Please give your comments";
			run();
		}else{
		//vub.tiamat.Tiamat.redraw();
		System.out.println("commentare:" + comments);
		MTTextArea temp = new MTTextArea(mtApplication, 0, 0, 500, 650,
				Tiamat.fontArial);
		temp.setAnchor(PositionAnchor.UPPER_LEFT);
		temp.setNoFill(true);
		temp.setText(comments);
		temp.setPickable(false);
		rectangle.addChild(temp);
		rectangle.setPickable(false);
		StartTiamat.general.addChild(rectangle);
		rectangle.setPositionRelativeToParent(new Vector3D(1650, 400));
		rectangle.addChild(playButton(mtApplication));
		rectangle.addChild(recordButton(mtApplication));
		rectangle.addChild(editButton(mtApplication));
		System.out.println("show ended");
		}
	}

	public static MTImageButton recordButton(final MTAndroidApplication mtApplication) {
		PImage arrow = mtApplication.loadImage(imagePath + "record.jpg");
		MTImageButton nextSceneButton = new MTImageButton(mtApplication, arrow);
		nextSceneButton.setNoStroke(true);
		if (MT4jSettings.getInstance().isOpenGlMode())
			nextSceneButton.setUseDirectGL(true);
		nextSceneButton.addGestureListener(TapProcessor.class,
				new IGestureEventListener() {
					public boolean processGestureEvent(MTGestureEvent ge) {
						TapEvent te = (TapEvent) ge;
						if (te.isTapped()) {
							// Save the current scene on the scene stack before
							// changing
							System.out.println("Record clicked");
							if(recording){
								System.out.println("Record Stopped");
								recording = false;
								recorder.stopRecording();
								
							}else{
								System.out.println("Record Started");
								recording = true;
								recorder.startRecording();

						}}
						return true;
					}
				});
		nextSceneButton.setAnchor(PositionAnchor.UPPER_LEFT);
		nextSceneButton.setPositionRelativeToParent(new Vector3D(30, 650));
		return nextSceneButton;

	}

	public static MTImageButton playButton(final MTAndroidApplication mtApplication) {
		PImage arrow = mtApplication.loadImage(imagePath + "Play.jpg");
		MTImageButton nextSceneButton = new MTImageButton(mtApplication, arrow);
		nextSceneButton.setNoStroke(true);
		if (MT4jSettings.getInstance().isOpenGlMode())
			nextSceneButton.setUseDirectGL(true);
		nextSceneButton.addGestureListener(TapProcessor.class,
				new IGestureEventListener() {
					public boolean processGestureEvent(MTGestureEvent ge) {
						TapEvent te = (TapEvent) ge;
						if (te.isTapped()) {
							// Save the current scene on the scene stack before
							// changing
							System.out.println("play clicked");
							if(playing){
								System.out.println("Play Stopped");
								playing = false;
								recorder.stopPlaying();
							}else {
								System.out.println("Play Started");
								playing = true;
								recorder.startPlaying();	
							
							// doSlideIn = false;

						}
						}
						return true;
					}
				});
		nextSceneButton.setAnchor(PositionAnchor.UPPER_LEFT);
		nextSceneButton.setPositionRelativeToParent(new Vector3D(130, 650));
		return nextSceneButton;
		// mapMenu.addChild(new MTRectangle(0,0,200,200,mtApplication));
		// mapMenu.addChild(nextSceneButton);
	}

	public static MTImageButton editButton(final MTAndroidApplication mtApplication) {
		PImage arrow = mtApplication.loadImage(imagePath + "EDIT.jpg");
		MTImageButton nextSceneButton = new MTImageButton(mtApplication, arrow);
		nextSceneButton.setNoStroke(true);
		if (MT4jSettings.getInstance().isOpenGlMode())
			nextSceneButton.setUseDirectGL(true);
		nextSceneButton.addGestureListener(TapProcessor.class,
				new IGestureEventListener() {
					public boolean processGestureEvent(MTGestureEvent ge) {
						TapEvent te = (TapEvent) ge;
						if (te.isTapped()) {
							// Save the current scene on the scene stack before
							// changing
							System.out.println("Record clicked");
							setActionText("Give the new comments");
							run();

						}
						return true;
					}
				});
		nextSceneButton.setAnchor(PositionAnchor.UPPER_LEFT);
		nextSceneButton.setPositionRelativeToParent(new Vector3D(230, 650));
		return nextSceneButton;

	}

	public static String run() {
		final AlertDialog.Builder alert = new AlertDialog.Builder(mtApplication);
		alert.setTitle("TIAMAT");
		alert.setMessage(getActionText());
		// Set an EditText view to get user input
		final EditText input = new EditText(mtApplication);
		alert.setView(input);
		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				Editable value = input.getText();
				ActionReply = value.toString();
				comments = ActionReply;
				// StartTiamat.general.removeChild(rectangle);
				rectangle.removeAllChildren();
				show();
				
				// Do something with value!
				// StartTiamat.general.addChild(rendering.Renderer.makeText(mtApplication,
				// value.toString()));
				// System.out.println(value);
			}
		});
		alert.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						// Canceled.
					}
				});
		mtApplication.runOnUiThread(new Runnable() {
			public void run() {
				// * The Complete ProgressBar does not appear
				alert.show();
			}
		});
		return comments;
	}
	public void hide(){
		StartTiamat.general.removeChild(rectangle);
	}
	public void setComments(String comments){
		this.comments = comments;
	}
	public boolean inUse(){
		return inUse;
	}

	public static String getActionText() {
		return ActionText;
	}

	public static void setActionText(String actionText) {
		ActionText = actionText;
	}
}
