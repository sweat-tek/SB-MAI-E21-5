package org.jhotdraw.geom;

import com.tngtech.jgiven.junit.ScenarioTest;
import java.awt.geom.Rectangle2D;
import org.junit.Test;

/**
 *
 * @author Jacob
 */
public class getBoundsTest extends ScenarioTest<GivenNodes, WhenGetBounds, ThenRectangleCreated> {
    
    @Test
    public void selectingLineAndMovingResultsInNewLineCreated () {
        // When moving a line, a rectangle is created to represent that line
        
        given().node(new BezierPath.Node(200d, 375d))
                .and().node(new BezierPath.Node(400d, 525d));
        
        when().getting_bounds();
        
        then().theResultingRectangleIs(new Rectangle2D.Double(200d, 375d, 200d, 150d));
        
    }
}
