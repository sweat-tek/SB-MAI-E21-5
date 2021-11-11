/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jhotdraw.draw.action;

import java.util.Arrays;
import javax.swing.JComponent;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.jhotdraw.draw.DefaultDrawingEditor;
import org.jhotdraw.draw.DefaultDrawingView;
import org.jhotdraw.draw.DrawingEditor;
import org.jhotdraw.draw.FigureSelectionListener;
import org.junit.Assert;

/**
 *
 * @author svane
 */
public class SelectionComponentRepainterTest {
    
    private SelectionComponentRepainter scr;
    
    public SelectionComponentRepainterTest() { 
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        DrawingEditor editor = new DefaultDrawingEditor();
        JComponent component = new JComponent() {};
        scr = new SelectionComponentRepainter(editor, component);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void addListeners() {
        DefaultDrawingView view = new DefaultDrawingView();
        
        Assert.assertFalse(Arrays.asList(view.getPropertyChangeListeners()).contains(scr));
        Assert.assertFalse(Arrays.asList(view.getListeners(FigureSelectionListener.class)).contains(scr));
        
        scr.addListeners(view);
        
        Assert.assertTrue(Arrays.asList(view.getPropertyChangeListeners()).contains(scr));
        Assert.assertTrue(Arrays.asList(view.getListeners(FigureSelectionListener.class)).contains(scr));
    }
    
    @Test
    public void removeListeners() {
        DefaultDrawingView view = new DefaultDrawingView();
        
        scr.removeListeners(view);
        
        Assert.assertFalse(Arrays.asList(view.getPropertyChangeListeners()).contains(scr));
        Assert.assertFalse(Arrays.asList(view.getListeners(FigureSelectionListener.class)).contains(scr));
    }
}
