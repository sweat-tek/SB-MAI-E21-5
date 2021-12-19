/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jhotdraw;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import java.util.HashSet;
import java.util.Set;
import org.jhotdraw.draw.CompositeFigure;
import org.jhotdraw.draw.DefaultDrawingView;
import org.jhotdraw.draw.DrawingEditor;
import org.jhotdraw.draw.Figure;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author lasse
 */
class ThenChanges extends Stage<ThenChanges>{
    
    @ExpectedScenarioState
    private DrawingEditor editor;
    
    @ExpectedScenarioState
    private int givenFigures;
    
    @ExpectedScenarioState
    private DefaultDrawingView defaultView;

    @ExpectedScenarioState
    private Set<Figure> selectedFigures;

    @ExpectedScenarioState
    private Set<Figure> nonSelectedFigures;
    
    
    public ThenChanges figuresAreDeleted() {
        assertEquals(0, defaultView.getDrawing().getChildCount());
        return self();
    }
    
    public ThenChanges onlySelectedFiguresAreDuplicated() {
        assertEquals(nonSelectedFigures.size() + givenFigures * 2, defaultView.getDrawing().getChildCount());
        return self();
    }
    
    public ThenChanges onlySelectedFiguresAreDeleted() {
        
        Set<Figure> selection = new HashSet<>(nonSelectedFigures);
        
        assertSetContainsFigure(editor.getActiveView().getDrawing(), selection);
        
        return self();
    }
    
    private void assertSetContainsFigure(Figure set, Set<Figure> children) {
        assertTrue(set instanceof CompositeFigure);
        CompositeFigure compFigure = (CompositeFigure) set;
        assertEquals(children.size(), compFigure.getChildCount());
        
        for(Figure child: children) {
            assertTrue(compFigure.contains(child));
        }
    }
}


