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
import vub.tiamat.StartTiamat;
import vub.tiamat.Tiamat;

/**
 * The begin menu class.
 * 
 * @author Ayrton Vercruysse
 * 
 */
public class BeginMenu extends Menu {
	/**
	 * Initializes the begin menu.
	 * 
	 * @param mtApplication
	 * @param name
	 */
	public BeginMenu(MTAndroidApplication mtApplication, String name) {
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
			final String label, final Menu menu, IFont font, float cellWidth,
			float cellHeight, final MTColor cellFillColor,
			final MTColor cellPressedFillColor) {
		makeCell(mtApplication, cellWidth, cellHeight, cellFillColor); // Creates
																		// one
																		// cell
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
							StartTiamat.general.removeChild(mapMenu);
							menu.Make(mtApplication, label, true);
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
		doSlideIn = out; // Check if the menu is to be called slided in, or
							// slided out.
		makeMapMenu(mtApplication, out);
		StartTiamat.general.addChild(mapMenu);
		makeList(mtApplication);
		list.setPositionRelativeToParent(mapMenu.getCenterPointLocal());
		mapMenu.addChild(list);
		// Creates the menu buttons.
		list.addListElement(this.createListCell(mtApplication, "Functions",
				vub.tiamat.Tiamat.functionsMenu, Tiamat.menuFont, cellWidth, cellHeight,
				cellFillColor, cellPressedFillColor));
		list.addListElement(this.createListCell(mtApplication, "Operations",
				vub.tiamat.Tiamat.operationsMenu, Tiamat.menuFont, cellWidth, cellHeight,
				cellFillColor, cellPressedFillColor));
		list.addListElement(this.createListCell(mtApplication, "Definitions",
				vub.tiamat.Tiamat.definitionsMenu, Tiamat.menuFont, cellWidth, cellHeight,
				cellFillColor, cellPressedFillColor));

		list.addListElement(this.createListCell(mtApplication, "Variables",
				vub.tiamat.Tiamat.variablesMenu, Tiamat.menuFont, cellWidth, cellHeight,
				cellFillColor, cellPressedFillColor));
		list.addListElement(this.createListCell(mtApplication, "My Functions",
				vub.tiamat.Tiamat.myFunctionsMenu, Tiamat.menuFont, cellWidth, cellHeight,
				cellFillColor, cellPressedFillColor));

		MultiPurposeInterpolator in = new MultiPurposeInterpolator(0, 170, 700,
				0.1f, 0.7f, 1);
		// Slide animations, makes the menu able to slide in and out.
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
