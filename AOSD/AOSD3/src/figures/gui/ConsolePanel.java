package figures.gui;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.StyleContext;

public class ConsolePanel extends JPanel {

    JTextArea text = new JTextArea();

    public ConsolePanel() {
        super(new BorderLayout());
        text.setFont(StyleContext.getDefaultStyleContext().getFont("SansSerif", Font.PLAIN, 10));
        JScrollPane scroller = new JScrollPane(text);
        scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(BorderLayout.CENTER, scroller);
    }

    public void println(String msg) {
        text.append(msg + '\n');
    }
}