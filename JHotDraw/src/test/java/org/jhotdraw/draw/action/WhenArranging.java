package org.jhotdraw.draw.action;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.BeforeStage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import java.util.HashSet;
import java.util.Set;
import org.jhotdraw.draw.DrawingEditor;
import org.jhotdraw.draw.Figure;
import org.jhotdraw.draw.action.arrange.*;

public class WhenArranging extends Stage<WhenArranging> {

    @ExpectedScenarioState
    @ProvidedScenarioState
    private DrawingEditor editor;

    @ProvidedScenarioState
    private Set<Figure> selectedFigures;

    @ProvidedScenarioState
    private Set<Figure> nonselectedFigures;

    private SendToBackAction sendToBackAction;
    private BringToFrontAction bringToFrontAction;

    @BeforeStage
    public void before() {
        sendToBackAction = new SendToBackAction(editor);
        bringToFrontAction = new BringToFrontAction(editor);

        selectedFigures = new HashSet<>(editor.getActiveView().getSelectedFigures());

        nonselectedFigures = new HashSet<>(editor.getActiveView().getDrawing().getChildren());
        nonselectedFigures.removeAll(selectedFigures);
    }

    WhenArranging sendingFiguresToBack() {
        sendToBackAction.actionPerformed(null);
        return this;
    }

    WhenArranging bringingFiguresToFront() {
        bringToFrontAction.actionPerformed(null);
        return this;
    }
}
