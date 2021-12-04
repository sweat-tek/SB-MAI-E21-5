/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jhotdraw.samples.svg.figures.scenarioTest;

import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import org.jhotdraw.draw.AttributeKey;
import org.jhotdraw.samples.svg.figures.SVGTextFigure;
import org.jhotdraw.util.ResourceBundleUtil;
import static org.junit.Assert.assertNotNull;

/**
 *
 * @author lucas
 */
public class ThenTextShow {
    @ExpectedScenarioState
    protected AttributeKey<String> text = new AttributeKey<>("text", String.class, "Text", false, labels);
    
    
    private static ResourceBundleUtil labels;
    
     public ThenTextShow textFigure_is_shown_without_any_null_exceptions() {
        SVGTextFigure textFigure = new SVGTextFigure("Text");  
        AttributeKey<Double> FILL_OPACITY = new AttributeKey<Double>("fillOpacity", Double.class, 1d, false, labels);      
        textFigure.setAttribute(FILL_OPACITY, 0.0);
        text.basicSet(textFigure, "hello");
        assertNotNull(text);
        return this;
    }
}
