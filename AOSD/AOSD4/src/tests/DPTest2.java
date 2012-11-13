package tests;

import java.awt.Shape;

import figures.FigureElement;
import figures.ShapeFigureElement;
import figures.gui.FigureSurface;
import figures.gui.FigurePanel;

public class DPTest2 extends CoreTest {
	
	public static class CountingFS extends FigureSurface {
		public CountingFS() {
			super(new FigurePanel());
		}
		
		public void repaint() {
			super.repaint();
			incr();
		}
		
		private int i = 0;
		public void incr() { i++; }
		public int getCount() { return i; }
	}
	
	private CountingFS dirty1, dirty2, dirty3;
	
	public void setUp() {
		super.setUp();
		dirty1 = new CountingFS();
		dirty2 = new CountingFS();
		dirty3 = new CountingFS();
	}
	
	private void addObserver(FigureElement s, FigureSurface o) {
		aspects.Ex2a_a.aspectOf().addObserver(s,o);
	}
	
	public final void testObservePoint1() {
		addObserver(p1,dirty1);
		addObserver(p1,dirty2);
		addObserver(p2,dirty3);
		int ex1 = dirty1.getCount();
		int ex2 = dirty2.getCount();
		int ex3 = dirty3.getCount();
		p1.move(10,10);
		assertEquals(ex1+1,dirty1.getCount());
		assertEquals(ex2+1,dirty2.getCount());
		assertEquals(ex3,dirty3.getCount());
	}
	
	public final void testObservePoint2() {
		addObserver(p1,dirty1);
		addObserver(p2,dirty2);
		int ex1 = dirty1.getCount();
		int ex2 = dirty2.getCount();
		p1.move(10,10);
		assertEquals(ex1+1,dirty1.getCount());
		assertEquals(ex2,dirty2.getCount());
		p2.move(10,10);
		assertEquals(ex1+1,dirty1.getCount());
		assertEquals(ex2+1,dirty2.getCount());
	}
	
	public final void testObserveGeneral() {
		FigureElement fe = new ShapeFigureElement() {
			public void move(int dx, int dy) {}
			public Shape getShape() { return null; }
		};
		addObserver(fe,dirty1);
		addObserver(fe,dirty2);
		int ex1 = dirty1.getCount();
		int ex2 = dirty2.getCount();
		fe.move(10,10);
		assertEquals(ex1+1,dirty1.getCount());
		assertEquals(ex2+1,dirty2.getCount());
	}

}
