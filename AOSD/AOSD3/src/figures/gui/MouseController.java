package figures.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import figures.gui.tools.DrawingTool;

import figures.Group;

public class MouseController implements MouseListener, MouseMotionListener {
	Group canvas;
	
	DrawingTool tool;
    
    public MouseController(Group canvas) {
    		this.canvas = canvas;
    }

    public void mousePressed(MouseEvent e){
        int x = e.getX(), y = e.getY();
        tool = DrawingTool.start(canvas,x,y);
      }
    
    public void mouseDragged(MouseEvent e) {
    		int x = e.getX(), y = e.getY();
    		tool = tool.update(x,y);
    }

    public void mouseReleased(MouseEvent e){
        mouseDragged(e);
        tool = null;
    }


    public void mouseMoved(MouseEvent e){}
    public void mouseClicked(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
}
