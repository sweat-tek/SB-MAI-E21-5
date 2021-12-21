/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jhotdraw.samples.svg.figures;

import com.tngtech.jgiven.junit.ScenarioTest;
import java.awt.Color;
import java.awt.*;
import org.jhotdraw.draw.AttributeKey;
import org.jhotdraw.samples.svg.SVGAttributeKeys;
import org.jhotdraw.samples.svg.figures.scenarioTest.GivenText;
import org.jhotdraw.samples.svg.figures.scenarioTest.ThenTextShow;
import org.jhotdraw.samples.svg.figures.scenarioTest.WhenChanged;
import org.jhotdraw.util.ResourceBundleUtil;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lucas
 */
public class SVGAttributeKeysTest extends ScenarioTest<GivenText, WhenChanged, ThenTextShow> {

    private static ResourceBundleUtil labels;

    public SVGAttributeKeysTest() {
    }

    @Test
    public void testForNoNullAttributesStrokePaint() {
        // make new textFigure 
        SVGTextFigure textFigure = new SVGTextFigure("Hello"); 

        // Instanciate STROKE_OPACITY AND STROKE COLOR
        AttributeKey<Double> STROKE_OPACITY = new AttributeKey<Double>("strokeOpacity",Double.class, 1d, false, labels);
        AttributeKey<Color> STROKE_COLOR = new AttributeKey<Color>("strokeColor", Color.class, Color.black, true, labels);

        // prints attributes before changes made
        System.out.println("New textFigure attributes:  " + textFigure.getAttributes());

        // set color to red
        Color red = new Color(255, 0, 0);
        textFigure.setAttribute(STROKE_COLOR, red);
        // shows the color changed to red
        System.out.println("textFigure attributes after changing color:  " + textFigure.getAttributes());

        // Set attribute FILL_OPACITY = 0.0 and FILL_COLOR = null
        textFigure.setAttribute(STROKE_OPACITY, 0.0);
        textFigure.setAttribute(STROKE_COLOR, null);

        // When getStrokePaint() is called now because stroke_opacity = 0 should change color to default
        // Set value from geFillPaint = paint and print returned color 
        Paint paint = SVGAttributeKeys.getStrokePaint(textFigure);
        //Should print color value (255, 255, 255)
        System.out.println("Printing paint value: " + paint);
        assertNotNull(paint);   
    }

    @Test
    public void testForNoNullAttributesFillPaint() {
        // make new textFigure 
        SVGTextFigure textFigure = new SVGTextFigure("Hello");   

        // Instanciate FILL_OPACITY AND FILL_COLOR
        AttributeKey<Double> FILL_OPACITY = new AttributeKey<Double>("fillOpacity", Double.class, 1d, false, labels);      
        AttributeKey<Color> FILL_COLOR = new AttributeKey<Color>("fillColor", Color.class, Color.white, true, labels);

        // prints attributes before changes made
        System.out.println("New textFigure attributes:  " + textFigure.getAttributes());

        // set color to red
        Color red = new Color(255, 0, 0);
        textFigure.setAttribute(FILL_COLOR, red);
        // shows the color changed to red
        System.out.println("textFigure attributes after changing color:  " + textFigure.getAttributes());

        // Set attribute FILL_OPACITY = 0.0 and FILL_COLOR = null 
        textFigure.setAttribute(FILL_OPACITY, 0.0);
        textFigure.setAttribute(FILL_COLOR, null);

        // When getStrokePaint() is called now because fill_opacity = 0 should change color to default
        // Set value from geFillPaint = paint and print returned color 
        Paint paint = SVGAttributeKeys.getFillPaint(textFigure);
        // Should print color value (255, 255, 255)
        System.out.println("Printing paint value: " + paint);
        assertNotNull(paint);
    }

    /* 
    // Acceptance test
    // Given i have a textFigure
    // When the user makes a change to something in the textFigure
    // Then the text is shown
    */
    @Test
     public void user_can_add_text_to_figures() {
        given().new_text_added();
        when().the_user_change_textFigure();
        then().textFigure_is_shown_without_any_null_exceptions();
    }
    
}