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
public class ThenFiguresSelected extends Stage<ThenFiguresSelected> {
    
    @ExpectedScenarioState
    private DefaultDrawingView drawingView;

    @ExpectedScenarioState
    private int numberOfFigures;
    
    public ThenFiguresSelected FiguresAreSelected() {
        Assert.assertEquals(numberOfFigures, drawingView.getSelectionCount());
        
        return self();
    }
}
