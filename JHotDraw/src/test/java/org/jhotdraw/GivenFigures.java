/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jhotdraw;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.BeforeStage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import org.jhotdraw.draw.BezierFigure;
import org.jhotdraw.draw.DefaultDrawingEditor;
import org.jhotdraw.draw.DefaultDrawingView;
import org.jhotdraw.draw.DrawingEditor;
import org.jhotdraw.draw.Figure;
import org.jhotdraw.draw.QuadTreeDrawing;

/**
 *
 * @author lasse
 */
class GivenFigures extends Stage<GivenFigures>{
    
    @ProvidedScenarioState
    private DrawingEditor editor;

    @ProvidedScenarioState
    private DefaultDrawingView defaultView;


    @BeforeStage
    private void before() {
        editor = new DefaultDrawingEditor();
        defaultView = new DefaultDrawingView();
        defaultView.setDrawing(new QuadTreeDrawing());
        editor.setActiveView(defaultView);
    }
    
    public GivenFigures oneSelectedFigure() {
        Figure f1 = new BezierFigure();
        editor.getActiveView().getDrawing().add(f1);

        editor.getActiveView().addToSelection(f1);
        
        return self();
    }
    
    public GivenFigures twoSelectedFigures() {
        Figure f1 = new BezierFigure();
        Figure f2 = new BezierFigure();
        editor.getActiveView().getDrawing().add(f1);
        editor.getActiveView().getDrawing().add(f2);

        editor.getActiveView().addToSelection(f1);
        editor.getActiveView().addToSelection(f2);
        
        return self();
    }
    
    public GivenFigures oneUnselectedFigure() {
        Figure f1 = new BezierFigure();
        editor.getActiveView().getDrawing().add(f1);

        editor.getActiveView().addToSelection(f1);
        
        return self();
    }
    
    public GivenFigures twoUnselectedFigures() {
        Figure f1 = new BezierFigure();
        Figure f2 = new BezierFigure();
        editor.getActiveView().getDrawing().add(f1);
        editor.getActiveView().getDrawing().add(f2);

        editor.getActiveView().addToSelection(f1);
        editor.getActiveView().addToSelection(f2);
        
        return self();
    }
    
}
