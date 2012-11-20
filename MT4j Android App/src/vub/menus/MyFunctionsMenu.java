package vub.menus;

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


import vub.ast.Node;
import vub.tiamat.StartTiamat;
import vub.tiamat.Tiamat;

public class MyFunctionsMenu extends Menu {
	public MyFunctionsMenu(MTAndroidApplication mtApplication, String name) {
		super(mtApplication, name);
	}

	private MTListCell createListCell(final MTAndroidApplication mtApplication,
			final String label, final vub.ast.Node node, IFont font,
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
								vub.ast.Node parent = StartTiamat.selected
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

	public void Make(MTAndroidApplication mtApplication, String name,
			Boolean out) {
		doSlideIn = out; // Check if the menu is to be called slided in, or
							// slided out.
		makeMapMenu(mtApplication, out);
		mapMenu.addChild(returnButton(mtApplication));
		StartTiamat.general.addChild(mapMenu);
		System.out.println("added menu");
		makeList(mtApplication);
		list.setPositionRelativeToParent(mapMenu.getCenterPointLocal());
		mapMenu.addChild(list);
		// Makes the buttons of the menu.
		for (int i = 0; i < StartTiamat.myFunctions.size(); i++) {
			vub.tiamat.Templates template = StartTiamat.myFunctions.get(i);		// For each element in the myFunctions vector a button gets created.
			list.addListElement(this.createListCell(mtApplication,
					template.getName(), template.getFunction(), Tiamat.menuFont,
					cellWidth, cellHeight, cellFillColor, cellPressedFillColor));
		}
		;

		MultiPurposeInterpolator in = new MultiPurposeInterpolator(0, 170, 700,
				0.1f, 0.7f, 1);
		final Animation slideOut = new Animation("slide out animation", in,
				mapMenu);
		// The slide animations.
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
