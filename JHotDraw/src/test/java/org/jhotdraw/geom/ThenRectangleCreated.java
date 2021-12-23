package org.jhotdraw.geom;

import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import java.awt.geom.Rectangle2D;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author Jacob
 */
public class ThenRectangleCreated {
    @ExpectedScenarioState
    private Rectangle2D.Double rectangle;
    
    ThenRectangleCreated theResultingRectangleIs(Rectangle2D.Double comparison) {
        assertEquals(rectangle, comparison);
        return this;
    }
}
