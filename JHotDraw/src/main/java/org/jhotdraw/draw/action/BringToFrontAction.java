/*
 * @(#)BringToFrontAction.java  2.0  2008-05-30
 *
 * Copyright (c) 2003-2008 by the original authors of JHotDraw
 * and all its contributors.
 * All rights reserved.
 *
 * The copyright of this software is owned by the authors and  
 * contributors of the JHotDraw project ("the copyright holders").  
 * You may not use, copy or modify this software, except in  
 * accordance with the license agreement you entered into with  
 * the copyright holders. For details see accompanying license terms. 
 */
package org.jhotdraw.draw.action;

import org.jhotdraw.draw.*;

/**
 * ToFrontAction.
 *
 * @author Werner Randelshofer
 * @version 2.0 2008-05-30 Renamed from MoveToFrontAction to BringToFrontAction
 * for consistency with the API of Drawing.
 * <br>1.0 24. November 2003 Created.
 */
public class BringToFrontAction extends SendToBackAction {

    public static String ID = "edit.bringToFront";

    /**
     * Creates a new instance.
     */
    public BringToFrontAction(DrawingEditor editor) {
        super(editor, ArrangeLayer.FRONT);
        labels.configureAction(this, ID);
    }
}
