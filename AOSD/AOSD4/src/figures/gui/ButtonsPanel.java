package figures.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ButtonsPanel extends JPanel {
	private final FigurePanel panel;
	JLabel msgs = new JLabel("click to add a point or line");
    public ButtonsPanel(FigurePanel panel) {
        this.panel = panel;
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.add(new JButton(new AbstractAction("Main") {
                public void actionPerformed(ActionEvent e) {
                    Main.main(new String[]{});
                    ButtonsPanel.this.panel.fs.repaint();
                }
            }));
        this.add(msgs);
    }

    public void log(String msg) {
        msgs.setText(msg);
    }
}