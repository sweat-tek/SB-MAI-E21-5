package org.jhotdraw.arrange.bdd;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.BeforeStage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import org.jhotdraw.draw.*;
import org.jhotdraw.samples.svg.figures.SVGRectFigure;

class GivenFiguresToArrange extends Stage<GivenFiguresToArrange> {

    @ProvidedScenarioState
    private DrawingEditor editor;

    @BeforeStage
    private void before() {
        editor = new DefaultDrawingEditor();
        DrawingView view = new DefaultDrawingView();
        view.setDrawing(new QuadTreeDrawing());
        editor.setActiveView(view);
    }

    GivenFiguresToArrange someSelectedFigures() {
        Figure f1 = new SVGRectFigure();
        Figure f2 = new SVGRectFigure();
        Figure f3 = new SVGRectFigure();
        editor.getActiveView().getDrawing().add(f1);
        editor.getActiveView().getDrawing().add(f2);
        editor.getActiveView().getDrawing().add(f3);
        editor.getActiveView().addToSelection(f1);
        editor.getActiveView().addToSelection(f2);
        editor.getActiveView().addToSelection(f3);
        return this;
    }

    GivenFiguresToArrange someUnselectedFigures() {
        Figure f1 = new SVGRectFigure();
        Figure f2 = new SVGRectFigure();
        Figure f3 = new SVGRectFigure();
        editor.getActiveView().getDrawing().add(f1);
        editor.getActiveView().getDrawing().add(f2);
        editor.getActiveView().getDrawing().add(f3);
        return this;
    }
}
