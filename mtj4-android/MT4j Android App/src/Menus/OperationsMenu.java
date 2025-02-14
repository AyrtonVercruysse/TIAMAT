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
import vub.tiamat.StartTiamat;
import vub.tiamat.Tiamat;

/**
 * The implementation of the operations menu.
 * 
 * @author Ayrton Vercruysse
 * 
 */

public class OperationsMenu extends Menu {
	public OperationsMenu(MTAndroidApplication mtApplication, String name) {
		super(mtApplication, name);
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
								Node newNode = (Node) node.clone();
								AST.Node parent = StartTiamat.selected
										.getParent();
								parent.setChild(StartTiamat.selected, newNode);
								newNode.setParent(parent);
								StartTiamat.selected = null;
								vub.tiamat.Tiamat.redraw();
							}
						}
						return false;
					}
				});
		return cell;
	}
	/**
	 * Makes the menu, gets it displayed.
	 */
	public void Make(MTAndroidApplication mtApplication, String name,
			Boolean out) {
		doSlideIn = out;	// Check if the menu is to be called slided in, or slided out.
		makeMapMenu(mtApplication, out);
		mapMenu.addChild(returnButton(mtApplication));
		StartTiamat.general.addChild(mapMenu);
		System.out.println("added menu");
		makeList(mtApplication);
		list.setPositionRelativeToParent(mapMenu.getCenterPointLocal());
		mapMenu.addChild(list);
		// Creates the buttons of the menu.
		for (int i = 0; i < StartTiamat.operations.size(); i++) {
			vub.tiamat.Templates template = StartTiamat.operations.get(i);	// For eacht element in the operations vector a button gets created.
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
}