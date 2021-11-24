package org.jhotdraw.arrange;

import org.jhotdraw.draw.ArrangeLayer;
import org.jhotdraw.draw.Figure;
import org.jhotdraw.draw.QuadTreeDrawing;
import org.jhotdraw.samples.svg.figures.SVGRectFigure;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author antje12
 */
public class QuadTreeDrawingTest {
    
    public QuadTreeDrawingTest() {
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
     * Test of arrange method, of class QuadTreeDrawing.
     */
    @Test
    public void testAddFigure() {
        QuadTreeDrawing instance = new QuadTreeDrawing();
        Figure figure = new SVGRectFigure();
        instance.add(figure);
        
        assert(instance.children.contains(figure));
    }
    
    @Test
    public void testArrangeBack() {
        QuadTreeDrawing instance = new QuadTreeDrawing();
        Figure figure1 = new SVGRectFigure();
        Figure figure2 = new SVGRectFigure();
        instance.add(figure1);
        instance.add(figure2);
        
        assert(instance.children.get(0) == figure1);
        instance.arrange(figure2, ArrangeLayer.BACK);
        assert(instance.children.get(0) == figure2);
    }
    
    @Test
    public void testArrangeFront() {
        QuadTreeDrawing instance = new QuadTreeDrawing();
        Figure figure1 = new SVGRectFigure();
        Figure figure2 = new SVGRectFigure();
        instance.add(figure1);
        instance.add(figure2);
        
        assert(instance.children.get(1) == figure2);
        instance.arrange(figure1, ArrangeLayer.FRONT);
        assert(instance.children.get(1) == figure1);
    }
}
