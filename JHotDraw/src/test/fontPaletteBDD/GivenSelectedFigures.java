package org.jhotdraw.fontPaletteBDD;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.BeforeStage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;

import org.jhotdraw.draw.BezierFigure;
import org.jhotdraw.draw.DefaultDrawingEditor;
import org.jhotdraw.draw.DefaultDrawingView;
import org.jhotdraw.draw.DrawingEditor;
import org.jhotdraw.draw.DrawingView;
import org.jhotdraw.draw.Figure;
import org.jhotdraw.draw.QuadTreeDrawing;
import org.jhotdraw.samples.svg.figures.SVGTextFigure;

public class GivenSelectedFigures extends Stage<GivenSelectedFigures> {

    @ProvidedScenarioState
    private DrawingEditor editor;

    @BeforeStage
    public void before() {
        editor = new DefaultDrawingEditor();
        DrawingView view = new DefaultDrawingView();
        view.setDrawing(new QuadTreeDrawing());
        editor.setActiveView(view);
    }

    GivenSelectedFigures aSetOfSelectedFigures() {
        Figure f1 = new SVGTextFigure();
        Figure f2 = new SVGTextFigure();
        Figure f3 = new SVGTextFigure();
        Figure f4 = new SVGTextFigure();
        Figure f5 = new BezierFigure();
        Figure f6 = new BezierFigure();
        Figure f7 = new BezierFigure();
        editor.getActiveView().getDrawing().add(f1);
        editor.getActiveView().getDrawing().add(f2);
        editor.getActiveView().getDrawing().add(f3);
        editor.getActiveView().getDrawing().add(f4);
        editor.getActiveView().getDrawing().add(f5);
        editor.getActiveView().getDrawing().add(f6);
        editor.getActiveView().getDrawing().add(f7);
        editor.getActiveView().addToSelection(f1);
        editor.getActiveView().addToSelection(f2);
        editor.getActiveView().addToSelection(f3);
        editor.getActiveView().addToSelection(f6);
        return this;
    }
}
