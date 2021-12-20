/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.jhotdraw.samples.svg.figures;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author Kaan
 */
class ThenFigureIsTransformed extends Stage<ThenFigureIsTransformed> {
    
    @ExpectedScenarioState
    protected SVGRectFigure fig;
    
    @ExpectedScenarioState
    protected double x;
    
    @ExpectedScenarioState
    protected double y;

    public ThenFigureIsTransformed rectIsTransformed() {
        assertEquals(x + 10.0, fig.getX(), 0.001);
        assertEquals(y + 10.0, fig.getY(), 0.001);
        
        return this;
    }
    
}
