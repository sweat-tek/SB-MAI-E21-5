package org.jhotdraw.geom;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.BeforeStage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.annotation.ScenarioState;
import java.awt.geom.Rectangle2D;
import java.util.List;
import static org.junit.Assert.assertNotNull;

/**
 *
 * @author Jacob
 */
public class WhenGetBounds extends Stage<WhenGetBounds>  {
    
    @ExpectedScenarioState
    private List<BezierPath.Node> nodes;
    
    @ExpectedScenarioState
    private BezierPath bezierPath;
    
    @ProvidedScenarioState
    private Rectangle2D.Double rectangle;
    
    @BeforeStage
    public void before() {
        bezierPath.addAll(nodes);
    }
    
    WhenGetBounds getting_bounds() {
        rectangle = bezierPath.getBounds2D();
        
        return this;
    }
}
