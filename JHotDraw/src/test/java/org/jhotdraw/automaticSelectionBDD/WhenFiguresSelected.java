/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jhotdraw.automaticSelectionBDD;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import org.jhotdraw.draw.DefaultDrawingView;
import org.junit.Assert;

/**
 *
 * @author svane
 */
public class WhenFiguresSelected extends Stage<WhenFiguresSelected> {
    
    @ExpectedScenarioState
    private DefaultDrawingView drawingView;
    
    public WhenFiguresSelected figuresSelected() {
        Assert.assertEquals(0, drawingView.getSelectionCount());
        
        drawingView.selectAll();
        
        Assert.assertNotEquals(0, drawingView.getSelectionCount());
        
        return self();
    }
}
