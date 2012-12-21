package figures;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public class CanvasPoint extends ShapeFigureElement {
	
	@Override
	public Shape getShape() {
		return new Ellipse2D.Float((float)250-10/2,
                (float)250-10/2,
                (float)10,
                (float)10);
	}

	@Override
	public void move(int dx, int dy) {
		throw new InternalError("Canvas cannot move");		
	}

}
