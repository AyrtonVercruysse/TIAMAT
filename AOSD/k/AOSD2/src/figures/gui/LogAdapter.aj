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

import support.Log;

public aspect LogAdapter {

    before(String s): execution(static void Log.write(String)) && args(s) {
        if (Main.panel != null) {
            Main.panel.cp.println(s);
        }
    }
}
