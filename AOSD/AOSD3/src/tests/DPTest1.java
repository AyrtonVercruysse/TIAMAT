package tests;

import java.awt.Shape;

import aspects.Observer;
import figures.FigureElement;
import figures.ShapeFigureElement;

public class DPTest1 extends CoreTest {
	
	boolean dirty1, dirty2;
	
	public void setUp() {
		super.setUp();
		dirty1 = false;
		dirty2 = false;
	}
	
	private Observer getObserver1(final FigureElement expected) {
		return new Observer(){
			public void update(FigureElement fe) {
				assertSame(fe,expected);
				dirty1 = true;
			}
		};
	}
	
	private Observer getObserver2(final FigureElement expected) {
		return new Observer(){
			public void update(FigureElement fe) {
				assertSame(fe,expected);
				dirty2 = true;
			}
		};
	}
	
	private Observer getFailObserver(final String msg) {
		return new Observer() {
			public void update(FigureElement fe) {
				fail(msg);
			}
		};
	}
	
	public final void testObservePoint1() {
		p1.addObserver(getObserver1(p1));
		p1.addObserver(getObserver2(p1));
		p2.addObserver(getFailObserver("wrong point"));
		assertTrue(!dirty1 && !dirty2);
		p1.move(10,10);
		assertTrue(dirty1 && dirty2);
	}
	
	public final void testObservePoint2() {
		p1.addObserver(getObserver1(p1));
		p2.addObserver(getObserver2(p2));
		assertTrue(!dirty1 && !dirty2);
		p1.move(10,10);
		assertTrue(dirty1 && !dirty2);
		p2.move(10,10);
		assertTrue(dirty1 && dirty2);
	}
	
	public final void testObserveGeneral() {
		FigureElement fe = new ShapeFigureElement() {
			public void move(int dx, int dy) {}
			public Shape getShape() { return null; }
		};
		fe.addObserver(getObserver1(fe));
		fe.addObserver(getObserver2(fe));
		assertTrue(!dirty1 && !dirty2);
		fe.move(10,10);
		assertTrue(dirty1 && dirty2);
	}

}
