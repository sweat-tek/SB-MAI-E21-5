/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jhotdraw.samples.svg.gui;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JButton;
import org.jhotdraw.draw.DrawingEditor;
import org.jhotdraw.draw.action.ToggleGridAction;
import org.jhotdraw.undo.UndoRedoManager;
import org.jhotdraw.util.ResourceBundleUtil;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sofie
 */
public class ActionsToolBarTest {
    
    public ActionsToolBarTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testjButtonCreate(){
        ActionsToolBar temp = new ActionsToolBar();
        temp.setUndoManager(new UndoRedoManager());
        JPanel p = new JPanel();
        ResourceBundleUtil labels = ResourceBundleUtil.getBundle("org.jhotdraw.samples.svg.Labels");
        JPanel testComp = temp.jButtonCreate(labels, p, 1);
        
        assertNotNull(testComp);
    }
}
