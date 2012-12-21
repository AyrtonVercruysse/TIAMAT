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

package tests;

import figures.*;


public class PointMovePostcondition extends CoreTest {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(PointMovePostcondition.class);
    }

    public void testNonMoving() {
        Point sp = new Point(10, 10) { public void move(int x, int y) {} };
        try {
            sp.move(10, 10);
            fail("should have thrown IllegalStateException");
        } catch (IllegalStateException e) { }
    }

    public void testMoving() {
        Point p1 = new Point(10, 100);
        p1.move(3, 30);
    }
    
    public void testFixedMoving() {
        Point p1 = new Point(10, 100) { public void move(int x, int y) { setX(13); setY(130); } };
        p1.move(3, 30);
        try {
        	p1.move(3, 30);
        	fail("should have thrown IllegalStateException");
        } catch (IllegalStateException e) { }

    }
}
