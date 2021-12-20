/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package org.jhotdraw.samples.svg.figures;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Collection;
import org.jhotdraw.draw.ConnectionFigure;
import org.jhotdraw.draw.Connector;
import org.jhotdraw.draw.Handle;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kaan
 */
public class SVGEllipseFigureTest {
    private SVGEllipseFigure fig;
    public SVGEllipseFigureTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        fig = new SVGEllipseFigure(30.0, 20.0, 30.0, 20.0);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void transformTest() {
        AffineTransform tx = new AffineTransform();
        tx.setToTranslation(10.0, 10.0);
        fig.transform(tx);
        
        assertEquals(30.0 + 10.0, fig.getX(), 0.001);
        assertEquals(20.0 + 10.0, fig.getY(), 0.001);
    }
   
    
    
}
