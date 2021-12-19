/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jhotdraw;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.BeforeStage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import java.util.HashSet;
import java.util.Set;
import org.jhotdraw.draw.DefaultDrawingView;
import org.jhotdraw.draw.DrawingEditor;
import org.jhotdraw.draw.Figure;


/**
 *
 * @author lasse
 */
class WhenEditing extends Stage<WhenEditing> {
    
    @ExpectedScenarioState
    private DefaultDrawingView defaultView;
    
    @ExpectedScenarioState
    @ProvidedScenarioState
    private DrawingEditor editor;

    @ProvidedScenarioState
    private int givenFigures;
    
    @ProvidedScenarioState
    private Set<Figure> nonSelectedFigures;

    @ProvidedScenarioState
    private Set<Figure> selectedFigures;
    
    @BeforeStage
    public void before() {
        selectedFigures = new HashSet<>(editor.getActiveView().getSelectedFigures());
        givenFigures = selectedFigures.size();
        nonSelectedFigures = new HashSet<>(editor.getActiveView().getDrawing().getChildren());
        nonSelectedFigures.removeAll(selectedFigures);
    }
    
    public WhenEditing duplicatingFigures() {
        defaultView.duplicate();
        
        return self();
    }
    
    public WhenEditing deletingFigures() {
        defaultView.delete();
        
        return self();
    }
}
