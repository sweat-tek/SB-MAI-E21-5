/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.jhotdraw.samples.svg.figures;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import java.awt.geom.AffineTransform;
import static org.junit.Assert.assertNotNull;

/**
 *
 * @author Kaan
 */
class WhenFigureTransformed extends Stage<WhenFigureTransformed>{
    
    @ExpectedScenarioState
    protected SVGRectFigure fig;

    public WhenFigureTransformed rectTransform() {
        AffineTransform tx = new AffineTransform();
        tx.setToTranslation(10.0, 10.0);
        fig.transform(tx);
        
        assertNotNull(fig);
        return this;
    }
    
}
