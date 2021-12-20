/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.jhotdraw.samples.svg.figures;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.annotation.BeforeStage;
import static org.junit.Assert.assertNotNull;

/**
 *
 * @author Kaan
 */
class GivenFigureDrawn extends Stage<GivenFigureDrawn>{

   @ProvidedScenarioState
    protected SVGRectFigure fig;
   
   @ProvidedScenarioState
    protected double x = 20.0;
    
   @ProvidedScenarioState
    protected double y = 10.0;    
    
    @BeforeStage
    public void drawRect() {
        fig = new SVGRectFigure(x, y, 20.0, 10.0);
    }
    
    public GivenFigureDrawn rectDrawn() {
        assertNotNull(fig);
        
        return this;
    }
    
}
