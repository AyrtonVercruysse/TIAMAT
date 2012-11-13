/* *******************************************************************
 * Copyright (c) 2002 Palo Alto Research Center, Incorporated (PARC).
 * All rights reserved.
 * This program and the accompanying materials are made available
 * under the terms of the Common Public License v1.0
 * which accompanies this distribution and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 *
 * Contributors:
 *     PARC     initial implementation
 * ******************************************************************/

package figures.gui;

import java.awt.*;
import javax.swing.*;

public class FigurePanel extends JComponent {

    ButtonsPanel bp = new ButtonsPanel(this);
    FigureSurface fs = new FigureSurface(this);
    ConsolePanel cp = new ConsolePanel();


    public FigurePanel() {
        setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(fs);
        panel.add(bp);
        JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panel, cp);
        sp.setPreferredSize(new Dimension(500, 400));
        sp.setDividerLocation(250);
        add(BorderLayout.CENTER, sp);
    }
    
    public FigureSurface getFigureSurface() {
    		return fs;
    }
    
    public ConsolePanel getConsolePanel() {
    		return cp;
    }

    final static Color BACKGROUND = Color.white;
}
