package Menus;

import org.mt4j.MTAndroidApplication;
import org.mt4j.components.MTComponent;
import org.mt4j.components.TransformSpace;
import org.mt4j.components.visibleComponents.shapes.MTRectangle;
import org.mt4j.components.visibleComponents.shapes.MTRoundRectangle;
import org.mt4j.components.visibleComponents.shapes.MTRectangle.PositionAnchor;
import org.mt4j.components.visibleComponents.widgets.MTListCell;
import org.mt4j.input.inputProcessors.IGestureEventListener;
import org.mt4j.input.inputProcessors.MTGestureEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapProcessor;
import org.mt4j.util.MTColor;
import org.mt4j.util.font.FontManager;
import org.mt4j.util.font.IFont;
import org.mt4j.util.math.Vector3D;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.text.Editable;
import android.util.Log;
import android.widget.EditText;

import rendering.Comments;

import AST.Comment;

import vub.tiamat.StartTiamat;
import vub.tiamat.Tiamat;

public class miniMenu extends Menu{
	MTRoundRectangle mapMenu;
	static MTAndroidApplication mtApplication;
	MTRectangle parent;
	private static String ActionText = "Please give the new name";
	static String newName;
	static String ActionReply;
	public miniMenu(MTAndroidApplication mtApplication, String name){
		super(mtApplication, name);
		this.mtApplication = mtApplication;
		mapMenu	 = new MTRoundRectangle(mtApplication, 0,0,0, 250,200, 20,20);
		mapMenu.setFillColor(new MTColor(35,35,35,180));
		mapMenu.setStrokeColor(new MTColor(35,35,35,180));
		IFont font = Tiamat.menuFont;
		makeList(mtApplication);
		list.setPositionRelativeToParent(mapMenu.getCenterPointLocal());
		mapMenu.addChild(list);
		// Creates the menu buttons.
		list.addListElement(this.createListCell(mtApplication, "Change Name",
				vub.tiamat.Tiamat.functionsMenu, font, cellWidth, cellHeight,
				cellFillColor, cellPressedFillColor, "changeName"));
		//if(StartTiamat.menuNode.getComments().inUse() == false){
		list.addListElement(this.createListCell(mtApplication, "Add Comments",
				vub.tiamat.Tiamat.operationsMenu, font, cellWidth, cellHeight,
				cellFillColor, cellPressedFillColor, "comments"));
		//}
		}
		
	
		
		private MTListCell createListCell(final MTAndroidApplication mtApplication,
				final String label, final Menu menu, IFont font, float cellWidth,
				float cellHeight, final MTColor cellFillColor,
				final MTColor cellPressedFillColor, final String sort) {
			makeCell(mtApplication, cellWidth, cellHeight, cellFillColor); 
			makeListLabel(mtApplication, font, label);
			cell.addChild(listLabel);
			listLabel.setPositionRelativeToParent(cell.getCenterPointLocal());
			cell.unregisterAllInputProcessors();
			cell.registerInputProcessor(new TapProcessor(mtApplication, 15));
			cell.addGestureListener(TapProcessor.class,
					new IGestureEventListener() {
						public boolean processGestureEvent(MTGestureEvent ge) {
							TapEvent te = (TapEvent) ge;
							if (te.isTapped()) {
								System.out.println("Button clicked: " + label);
								//StartTiamat.menuNode;
								if(sort == "comments"){
								Comments comments = StartTiamat.menuNode.getComments();
								comments.show();
								hide();
			}else if (sort == "changeName"){
				
				String newName = askName();
				((AST.Value) StartTiamat.menuNode).setName(newName);
				
				
			}else {System.out.println("Wrong button");}
							}
							return false;
						}
					});
			return cell;
		}

	public static String askName() {
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
				newName = ActionReply;
				// StartTiamat.general.removeChild(rectangle);
				
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
		return newName;
	}
	
	
	
	public static String getActionText() {
		return ActionText;
	}
	
	public void show(MTRectangle parent){
		this.parent = parent;
		
		parent.addChild(mapMenu);
		float width = parent.getWidthXY(TransformSpace.RELATIVE_TO_PARENT);
		mapMenu.setPositionRelativeToParent(new Vector3D(125+width,100));
	}
	public void hide(){
		parent.removeChild(mapMenu);
		
	}

	@Override
	public void Make(MTAndroidApplication mtApplication, String name,
			Boolean out) {
		// TODO Auto-generated method stub
		
	}

}
