/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jhotdraw.automaticSelectionBDD;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import org.jhotdraw.draw.DefaultDrawing;
import org.jhotdraw.draw.DefaultDrawingView;
import org.jhotdraw.draw.LineFigure;
import org.junit.Assert;


/**
 *
 * @author svane
 */
public class GivenFiguresDrawn extends Stage<GivenFiguresDrawn> {
   
    @ProvidedScenarioState
    private final DefaultDrawingView drawingView;
    
    @ProvidedScenarioState
    private final int numberOfFigures = 10;
    
    public GivenFiguresDrawn() {
        drawingView = new DefaultDrawingView();
        
        drawingView.setDrawing(new DefaultDrawing());
        
        for(int i = 0; i < numberOfFigures; i++) {
            drawingView.getDrawing().add(new LineFigure());
        }
    }
    
    public GivenFiguresDrawn figuresDrawn() {
        Assert.assertNotNull(drawingView.getDrawing());
        Assert.assertEquals(numberOfFigures, drawingView.getDrawing().getChildren().size());
        
        return self();
    }
}
