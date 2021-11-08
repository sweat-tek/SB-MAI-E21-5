/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jhotdraw.draw.action;


import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import org.jhotdraw.draw.DrawingView;
import org.jhotdraw.draw.FigureEvent;

/**
 *
 * @author svane
 */
public interface AbstractComponentRepainter extends PropertyChangeListener {
    
    // Methods for PropertyChangeListener
    public void propertyChange(PropertyChangeEvent evt);
    
   // Methods implements in both SelectionComponentPainter &
   // DrawingComponentRepainter
    public void attributeChanged(FigureEvent evt);
    public void dispose();
    
    // Methods for Listeners
    public void addListeners(DrawingView view);
    public void removeListeners(DrawingView view);
    
    //Methods for different views
    public void activeViewPropertyChangedHandler(PropertyChangeEvent evt);
    public void drawingPropertyChangedHandler(PropertyChangeEvent evt);
}
