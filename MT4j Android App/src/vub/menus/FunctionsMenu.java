package vub.menus;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;

import org.mt4j.MTAndroidApplication;
import org.mt4j.components.MTComponent;
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
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

import android.R;
import android.app.Activity;
import android.content.res.AssetManager;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import android.content.res.AssetManager;
import android.sax.Element;

import vub.ast.Node;
import vub.tiamat.StartTiamat;
import vub.tiamat.Tiamat;

public class FunctionsMenu extends Menu {
	public FunctionsMenu(MTAndroidApplication mtApplication, String name) {
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
							if (StartTiamat.selected == null) {
								System.out.println("There is no node selected");
							} else {
								System.out.println("Tapped and " + node);
								Node newNode = (Node) node.clone();
								
								vub.ast.Node parent = StartTiamat.selected.getParent();
								parent.setChild(StartTiamat.selected,
										(vub.ast.Node) newNode);
								((vub.ast.Node) newNode).setParent(parent);
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
		// The buttons get created.

		for (int i = 0; i < StartTiamat.functions.size(); i++) {
			vub.templates.Templates template = StartTiamat.functions.get(i); // For
																			// each
																			// element
																			// in
																			// the
																			// functions
																			// vector
																			// a
																			// button
																			// get
																			// created.
			list.addListElement(this.createListCell(mtApplication,
					template.getName(), template.getFunction(),
					Tiamat.menuFont, cellWidth, cellHeight, cellFillColor,
					cellPressedFillColor));
		}
		;

		try {

		} catch (Exception e) {
			System.out.println("root In de catch");
			e.printStackTrace();
		}

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
						if (((TapEvent) ge).getTapID() == TapEvent.BUTTON_CLICKED) {
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
