/**
 * @(#)SelectionComponentRepainter.java  1.0  23.05.2008
 *
 * Copyright (c) 2008 by the original authors of JHotDraw
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

import java.beans.*;
import javax.swing.*;
import org.jhotdraw.draw.*;

/**
 * Calls repaint on components, which show attributes of the drawing editor
 * and of its views based on the current selection.
 *
 * @author Werner Randelshofer
 * @version 1.0 23.05.2008 Created.
 */

// Implements AbstractComponentRepainter interface since a lot of the methods
// for SelectionComponentRepainter & DrawingComponentRepainter are the same
public class SelectionComponentRepainter extends AbstractComponentRepainter
        implements FigureSelectionListener {

    // Constructor uses addListeners to reduce the code 
    public SelectionComponentRepainter(DrawingEditor editor, JComponent component) {
        super(editor, component);
    }
    
    @Override
    public void selectionChanged(FigureSelectionEvent evt) {
        component.repaint();
    }
    
    /**
     * Adds the listeners
     * @param view paints a Drawing on a JComponent
     */
    @Override
    public void addListeners(DrawingView view) {
        view.addPropertyChangeListener(this);
        view.addFigureSelectionListener(this);
                
        if (view.getDrawing() != null) {
            view.getDrawing().addFigureListener(this);
        }
    }
    
    /**
     * Removes the listeners
     * @param view paints a Drawing on a JComponent
     */
    @Override
    public void removeListeners(DrawingView view) {
        view.removePropertyChangeListener(this);
        view.removeFigureSelectionListener(this);
        
        if (view.getDrawing() != null) {
            view.getDrawing().removeFigureListener(this);
        }
    }
    
    /**
     * Updates the old values with the new values by removing the old
     * listeners and adding the new listeners when the values are changed
     * for the Active View 
     * @param evt the values for the DrawingView Object
     */
    @Override
    public void activeViewPropertyChangedHandler(PropertyChangeEvent evt) {
        DrawingView view = (DrawingView) evt.getOldValue();
        
        if (view != null) {
            this.removeListeners(view);
        }
        
        view = (DrawingView) evt.getNewValue();
        
        if (view != null) {
           this.addListeners(view);
        }
        
        component.repaint();
    }
    
    /**
     * Updates the old values with the new values by removing the old
     * listeners and adding the new listeners when the values are changed
     * for the Drawing
     * @param evt the values for the DrawingView Object
     */
    public void drawingPropertyChangedHandler(PropertyChangeEvent evt) {
        Drawing drawing = (Drawing) evt.getOldValue();
        
        if (drawing != null) {
            drawing.removeFigureListener(this);
        }
        
        drawing = (Drawing) evt.getNewValue();
        
        if (drawing != null) {
            drawing.addFigureListener(this);
        }
        
        component.repaint();
    }
    
    
    
  
}

