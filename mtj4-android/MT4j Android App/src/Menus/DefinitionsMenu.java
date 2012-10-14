package Menus;

import org.mt4j.MTAndroidApplication;
import org.mt4j.components.interfaces.IMTComponent3D;
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
import org.mt4j.util.font.FontManager;
import org.mt4j.util.font.IFont;
import org.mt4j.util.math.Vector3D;

import AST.Node;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.Editable;
import android.widget.EditText;
import vub.tiamat.StartTiamat;
import vub.tiamat.Templates;
import vub.tiamat.Tiamat;

/**
 * The implementation of the Definitions menu.
 * 
 * @author Ayrton Vercruysse
 * 
 */
public class DefinitionsMenu extends Menu {
	static AST.Node thisNode;
	static String cont;
	static String ActionText;
	static String ActionReply;
	MTAndroidApplication mtApplication;

	public DefinitionsMenu(MTAndroidApplication mtApplication, String name) {
		super(mtApplication, name);
		this.mtApplication = mtApplication;
	}
	/**
	 * Creates one item of the menu.
	 * 
	 * @param mtApplication
	 * @param label
	 *            The of the button.
	 * @param menu
	 *            The menu in which the button is placed.
	 * @param font
	 *            The font of the name of the button.
	 * @param cellWidth
	 *            The width of the button.
	 * @param cellHeight
	 *            The hight of the button.
	 * @param cellFillColor
	 *            The fill color of the button.
	 * @param cellPressedFillColor
	 *            The color of the button when pressed.
	 * @return Returns a cell item, a button of the menu.
	 */
	private MTListCell createListCell(final MTAndroidApplication mtApplication,
			final String label, final AST.Node node, IFont font,
			float cellWidth, float cellHeight, final MTColor cellFillColor,
			final MTColor cellPressedFillColor) {
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
							cell.setFillColor(cellFillColor);
							if (StartTiamat.selected == null) {
								System.out.println("There is no node selected");
							} else {
								if (node instanceof AST.Value) {
									ActionText = "Give the value you want to enter";
									run();
									thisNode = (Node) node.clone();
									cont = "value";

								} else if (node instanceof AST.FunctionDefinition) {
									ActionText = "Give the name of the Function";
									System.out.println("FD1");
									run();
									thisNode = (Node) node.clone();
									cont = "function1";
								} else if (node instanceof AST.TableDefinition) {
									ActionText = "Give the name of the Table";
									run();
									thisNode = (Node) node.clone();
									cont = "table1";

								} else if (node instanceof AST.Definition) {
									ActionText = "Give a name for the definition";
									run();
									thisNode = (Node) node.clone();
									cont = "definition";

								}else{
									Node newNode = (Node) node.clone();
									AST.Node parent = StartTiamat.selected.getParent();
									parent.setChild(StartTiamat.selected, (AST.Node) newNode);
									((AST.Node) newNode).setParent(parent);
									StartTiamat.selected = null;
									vub.tiamat.Tiamat.redraw();
								}
							}
						}
						return false;
					}
				});
		return cell;
	}
/**
 * The cont functions has the continuations of different definition calls.
 * The continuations are the things that have to be done after input from the user by use of the keyboard is required.
 */
	private void cont() {
		// The continuations for when a value is defined.
		if (cont == "value") {
			((AST.Value) thisNode).setName(ActionReply);		// The name of the new value gets set.
			AST.Node parent = StartTiamat.selected.getParent();	// The parent of the selected node.
			parent.setChild(StartTiamat.selected, thisNode);	// The child of the parent gets set to the new node, in stead of the selected node.
			thisNode.setParent(parent);							// The parent of the new node gets set to the parent.
			StartTiamat.selected = null;						// We unselect the current node.
			vub.tiamat.Tiamat.redraw();							// Rerendering.
		}
		// The first continuation of the definition of a function.
		if (cont == "function1") {
			System.out.println("FD2");
			AST.Value name = new AST.Value(thisNode, ActionReply);	// We create a value with the name of the function.
			((AST.FunctionDefinition) thisNode).setName(name);		// We set the name of the function to this value.	
			ActionText = "Give the number of arguements";			// A new text for the keyboard action.
			run();													// The keyboard actions gets called.
			cont = "function2";		
			System.out.println("FD3");
		} else {
			// The second continuation of the defintion of a function.
			if (cont == "function2") {
				System.out.println("FD4");
				((AST.FunctionDefinition) thisNode).setArgumentList(Integer		// An agrumentlist for the function gets created.
						.parseInt(ActionReply));
				AST.Node parent = StartTiamat.selected.getParent();				// We get the parent of the selected node.
				parent.setChild(StartTiamat.selected, thisNode);				// We set the selected node to the new function.
				thisNode.setParent(parent);										// The new function node gets a parent.
				AST.FunctionCall call = new AST.FunctionCall(null,				// We create a call for the new function.
						((AST.FunctionDefinition) thisNode));
				Templates template = new Templates(call.getName(), call);		// We make a template of this call
				vub.tiamat.StartTiamat.myFunctions.add(template);				// and add it to de myFunctions vector.
				StartTiamat.selected = null;									// We unselect the node.
				cont = null;												
				vub.tiamat.Tiamat.redraw();
				System.out.println("FD5");
			}
		}
		// The first continuation of the table defitnition.
		if (cont == "table1") {
			AST.Value name = new AST.Value(thisNode,					// We create a value as name of this table.
					vub.tiamat.Tiamat.t.getText());
			((AST.TableDefinition) thisNode).setName(name);				// We set the name of the table.
			ActionText = "Give the number of elements";					// We set the messageof the keyboard action.	
			run();														// We call the keyboard action.
			cont = "table2";

		} else {
			// The second continuation of the table definition.
			if (cont == "table2") {
				AST.Value numberOfElements = new AST.Value(thisNode,		// We create a value with the number of elements.
						ActionReply);
				((AST.TableDefinition) thisNode)							// We set the number of elements of the new table.
						.setNumberOfElements(numberOfElements);
				AST.Node parent = StartTiamat.selected.getParent();			// We get the parent  of the selected node.
				parent.setChild(StartTiamat.selected, thisNode);			// We set the selected node to the new table.
				thisNode.setParent(parent);									// We give the new table a parent.
				AST.TableCall call = new AST.TableCall(null,				// We create a call for the new table
						((AST.TableDefinition) thisNode));
				Templates template = new Templates(call.getName(), call);	// We make a template with this call.
				vub.tiamat.StartTiamat.myFunctions.add(template);			// and add it to the myFunctions vector.
				StartTiamat.selected = null;
				vub.tiamat.Tiamat.redraw();

			}
		}
		// The continuation for when an assignment of a variable get done.
		if (cont == "definition") {
			AST.Value name = new AST.Value(thisNode, ActionReply);		// We give the definition a name.
			((AST.Definition) thisNode).setName(name);					// We set the definition to this name.
			AST.Node parent = StartTiamat.selected.getParent();			// We get the parent of the selected node.
			parent.setChild(StartTiamat.selected, thisNode);			// We set the selected node to the definition.
			thisNode.setParent(parent);									// We set the parent of the new node.
			AST.Value call = new AST.Value(null, name.getName());		// We make a call for this definition.
			Templates template = new Templates(call.getName(), call);	// We make a template with this call,
			vub.tiamat.StartTiamat.variables.add(template);				// and add it to the variables vector.
			StartTiamat.selected = null;
			vub.tiamat.Tiamat.redraw();
		}
	}
	/**
	 * Makes the menu, gets it displayed.
	 */
	public void Make(MTAndroidApplication mtApplication, String name,
			Boolean out) {
		doSlideIn = out;		// Boolean wheter the menu get called slided in or out.
		makeMapMenu(mtApplication, out);
		mapMenu.addChild(returnButton(mtApplication));
		StartTiamat.general.addChild(mapMenu);
		System.out.println("added menu");
		makeList(mtApplication);
		list.setPositionRelativeToParent(mapMenu.getCenterPointLocal());
		mapMenu.addChild(list);
		// The menu buttons get added.
		for (int i = 0; i < StartTiamat.definitions.size(); i++) {
			vub.tiamat.Templates template = StartTiamat.definitions.get(i); 	// For eacht element in the defition vector a button gets created.
			list.addListElement(this.createListCell(mtApplication,
					template.getName(), template.getFunction(), Tiamat.menuFont,
					cellWidth, cellHeight, cellFillColor, cellPressedFillColor));
		}
		;
		MultiPurposeInterpolator in = new MultiPurposeInterpolator(0, 170, 700,
				0.1f, 0.7f, 1);
		// The slide animations.
		final Animation slideOut = new Animation("slide out animation", in,
				mapMenu);
		slideOut.addAnimationListener(new IAnimationListener() {
			public void processAnimationEvent(AnimationEvent ae) {
				float delta = ae.getCurrentStepDelta();
				((IMTComponent3D) ae.getTargetObject())
						.translateGlobal(new Vector3D(delta, 0, 0));
				switch (ae.getId()) {
				case AnimationEvent.ANIMATION_ENDED:
					doSlideIn = true;
					animationRunning = false;
					break;
				}
			}
		});

		final Animation slideIn = new Animation("slide out animation", in,
				mapMenu);
		slideIn.addAnimationListener(new IAnimationListener() {
			public void processAnimationEvent(AnimationEvent ae) {
				float delta = -ae.getCurrentStepDelta();
				((IMTComponent3D) ae.getTargetObject())
						.translateGlobal(new Vector3D(delta, 0, 0));
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
		mapMenu.addGestureListener(TapProcessor.class,
				new IGestureEventListener() {
					public boolean processGestureEvent(MTGestureEvent ge) {
						TapEvent te = (TapEvent) ge;
						if (te.isTapped()) {
							if (!animationRunning) {
								animationRunning = true;
								if (doSlideIn) {
									slideIn.start();
								} else {
									slideOut.start();
								}
							}
						}
						return false;
					}
				});

	};

	public void run() {
		final AlertDialog.Builder alert = new AlertDialog.Builder(mtApplication);
		alert.setTitle("TIAMAT");
		alert.setMessage(ActionText);
		// Set an EditText view to get user input
		final EditText input = new EditText(mtApplication);
		alert.setView(input);
		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				Editable value = input.getText();
				ActionReply = value.toString();
				cont();
				// Do something with value!
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
	}

}
