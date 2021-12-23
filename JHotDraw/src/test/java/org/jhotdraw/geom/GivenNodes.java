package org.jhotdraw.geom;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.BeforeStage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import java.util.ArrayList;
import java.util.List;
import org.jhotdraw.geom.BezierPath;
import org.jhotdraw.geom.BezierPath.*;

/**
 *
 * @author Jacob
 */
public class GivenNodes extends Stage<GivenNodes> {
    
    @ProvidedScenarioState
    private List<BezierPath.Node> nodes;
    
    @ProvidedScenarioState
    private BezierPath bezierPath;
    
    
    @BeforeStage
    private void before() {
        nodes = new ArrayList<BezierPath.Node>();
        bezierPath = new BezierPath();
    }
    
    GivenNodes node(Node node){
        nodes.add(node);
        return this;
    }
    
}
