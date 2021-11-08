/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jhotdraw.draw.action;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JComponent;
import org.jhotdraw.draw.DrawingEditor;
import org.jhotdraw.draw.DrawingView;
import org.jhotdraw.draw.FigureAdapter;
import org.jhotdraw.draw.FigureEvent;

/**
 *
 * @author svane
 */
public class AbstractComponentRepainter extends FigureAdapter 
        implements PropertyChangeListener {
    
    public DrawingEditor editor;
    public JComponent component;
    
    public AbstractComponentRepainter(DrawingEditor editor, JComponent component) {
        this.editor = editor;
        this.component = component;
        if (editor != null) {
            if (editor.getActiveView() != null) {
                DrawingView view = editor.getActiveView();
                this.addListeners(view);
            }
            editor.addPropertyChangeListener(this);
        }
    }
    
    @Override
    public void attributeChanged(FigureEvent evt) {
        component.repaint();
    }
    
    /**
     * Rewrote the propertyChange() to use the 
     * activeViewPropertyChangedHandler() or the drawingPropertyChangedHandler()
     * depending on if the propertyName hits the Active View or the DrawingView
     * @param evt A PropertyChangeEvent object describing the event source and 
     * the property that has changed to determine if the method executes the logic
     * for activeViewPropertyChangedHandler() or 
     * drawingPropertyChangedHandler()
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String name = evt.getPropertyName();
        
        if (name == DrawingEditor.ACTIVE_VIEW_PROPERTY) {
            this.activeViewPropertyChangedHandler(evt);
        } else if (name == DrawingView.DRAWING_PROPERTY) {
            this.drawingPropertyChangedHandler(evt);
        } else {
            component.repaint();
        }
    }
    
    // Used removeListeners to reduce the code 
    public void dispose() {
        if (editor != null) {
            if (editor.getActiveView() != null) {
                DrawingView view = editor.getActiveView();
                this.removeListeners(view);
            }
            editor.removePropertyChangeListener(this);
            editor = null;
        }
        component = null;
    }
    
    public void addListeners(DrawingView view) {};
    
    public void removeListeners(DrawingView view) {};
    
    public void activeViewPropertyChangedHandler(PropertyChangeEvent evt) {};
    
    public void drawingPropertyChangedHandler(PropertyChangeEvent evt) {};
}
