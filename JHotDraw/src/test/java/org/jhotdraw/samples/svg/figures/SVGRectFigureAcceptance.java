/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package org.jhotdraw.samples.svg.figures;

import com.tngtech.jgiven.junit.ScenarioTest;
import org.junit.Test;

/**
 *
 * @author Kaan
 */
public class SVGRectFigureAcceptance extends ScenarioTest<GivenFigureDrawn, WhenFigureTransformed, ThenFigureIsTransformed>{
    
    @Test
    public void automaticSelection() {
        given().rectDrawn();
        when().rectTransform();
        then().rectIsTransformed();
    }
    
}
