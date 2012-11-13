package figures.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

import figures.Group;
import figures.Point;

public class FigureSurface extends JPanel {
    Group canvas;
    FigurePanel panel;

    public FigureSurface(FigurePanel panel) {
    		this.panel = panel;
    		canvas = new Group();
        MouseController mc = new MouseController(canvas);
        addMouseMotionListener(mc);
        addMouseListener(mc);
        setPreferredSize(new Dimension(500,500));
    }
    
    public Group getCanvas() {
    		return canvas;
    }
    
    public FigurePanel getFigurePanel() {
    		return panel;
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setPaint(FigurePanel.BACKGROUND);
        g2.fill(new Rectangle2D.Float(0f, 0f, (float)g2.getClipBounds().width, (float)g2.getClipBounds().height));
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        canvas.paint(g2);
    }

}