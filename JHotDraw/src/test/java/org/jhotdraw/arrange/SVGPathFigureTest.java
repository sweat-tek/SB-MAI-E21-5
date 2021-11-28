package org.jhotdraw.arrange;

import java.awt.geom.Rectangle2D;
import org.jhotdraw.samples.svg.figures.SVGPathFigure;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author antje12
 */
public class SVGPathFigureTest {
    
    public SVGPathFigureTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getDrawingArea method, of class SVGPathFigure.
     */
    @Test
    public void testGetDrawingArea() {
        SVGPathFigure instance = new SVGPathFigure();
        
        Rectangle2D.Double expResult = new Rectangle2D.Double(-2, -2, 4, 4);
        Rectangle2D.Double result = instance.getDrawingArea();
        
        assertEquals(expResult.x, result.x, 0);
        assertEquals(expResult.y, result.y, 0);
        assertEquals(expResult.width, result.width, 0);
        assertEquals(expResult.height, result.height, 0);
    }
}
