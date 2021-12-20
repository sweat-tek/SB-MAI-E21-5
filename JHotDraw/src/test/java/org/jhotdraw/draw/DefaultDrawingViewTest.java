/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jhotdraw.draw;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lasse
 */
public class DefaultDrawingViewTest {
    
    private DefaultDrawingView drawingView;
    private DrawingEditor editor;
    
    public DefaultDrawingViewTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        editor = new DefaultDrawingEditor();
        drawingView = new DefaultDrawingView();
        drawingView.setDrawing(new QuadTreeDrawing());
        editor.setActiveView(drawingView);
    }
    
    @After
    public void tearDown() {
    }
    
    private void setupSelectionFigures() {
        Figure f1 = new BezierFigure();
        Figure f2 = new BezierFigure();
        editor.getActiveView().getDrawing().add(f1);
        editor.getActiveView().getDrawing().add(f2);

        editor.getActiveView().addToSelection(f1);
        editor.getActiveView().addToSelection(f2);
    }
    
    private void setupFigures() {
        Figure f1 = new BezierFigure();
        Figure f2 = new BezierFigure();
        editor.getActiveView().getDrawing().add(f1);
        editor.getActiveView().getDrawing().add(f2);
    }
    
    
    /**
     * Test of delete method, of class DefaultDrawingView.
     */
    @Test
    public void testDelete() {
        setupSelectionFigures();
        
        // Check whether we have created and selected the two figures
        assertEquals(2, editor.getActiveView().getDrawing().getChildCount());
        
        drawingView.delete();
        
        // Check whether we have deleted the two figures so should now be zero
        assertEquals(0, editor.getActiveView().getDrawing().getChildCount());
    }
    
    /**
     * Worst case test of delete method, of class DefaultDrawingView.
     */
    @Test
    public void testDeleteNoneSelected() {
        setupFigures();
        
        // Check whether we have created and selected the two figures
        assertEquals(2, editor.getActiveView().getDrawing().getChildCount());
        
        drawingView.delete();
        
        // Check whether we have deleted the two figures so should now be zero
        assertEquals(2, editor.getActiveView().getDrawing().getChildCount());
    }

    /**
     * Test of duplicate method, of class DefaultDrawingView.
     */
    @Test
    public void testDuplicate() {
        setupSelectionFigures();
        
        // Check whether we have created and selected the two figures
        assertEquals(2, editor.getActiveView().getDrawing().getChildCount());
        
        drawingView.duplicate();
        
        // Check whether we have duplicated and selected the two figures so should now be four
        assertEquals(4, editor.getActiveView().getDrawing().getChildCount());
    }
    
    /**
     * Worst case test of duplicate method, of class DefaultDrawingView.
     */
    @Test
    public void testDuplicateNoneSelected() {
        setupFigures();
        
        // Check whether we have created and selected the two figures
        assertEquals(2, editor.getActiveView().getDrawing().getChildCount());
        
        drawingView.duplicate();
        
        // Check whether we have duplicated and selected the two figures so should now be four
        assertEquals(2, editor.getActiveView().getDrawing().getChildCount());
    }
}
