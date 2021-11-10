package org.jhotdraw.draw.action;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.jhotdraw.draw.DrawingEditor;
import org.jhotdraw.draw.Figure;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

class ThenFiguresArranged extends Stage<ThenFiguresArranged> {

    @ExpectedScenarioState
    private DrawingEditor editor;

    @ExpectedScenarioState
    private Set<Figure> selectedFigures;

    @ExpectedScenarioState
    private Set<Figure> nonselectedFigures;

    ThenFiguresArranged theSelectedFiguresAreStillSelected() {
        // The originally selected figures are still selected
        Set<Figure> selectedViewFigures = editor.getActiveView().getSelectedFigures();
        assertFigureContains(selectedViewFigures, selectedFigures);

        // The originally selected and nonselected figures are the only figures in the view
        Set<Figure> expectedFigures = new HashSet<>(selectedFigures);
        expectedFigures.addAll(nonselectedFigures);
        Set<Figure> viewFigures = new HashSet<>(editor.getActiveView().getDrawing().getChildren());
        assertFigureContains(viewFigures, expectedFigures);

        return this;
    }

    ThenFiguresArranged theSelectedFiguresAreBehindTheOthers() {
        List<Figure> viewFigures = editor.getActiveView().getDrawing().getChildren();
        int end = selectedFigures.size();
        
        Set<Figure> backSet = new HashSet<>(viewFigures.subList(0, end));
        assertFigureContains(backSet, selectedFigures);
        
        return this;
    }

    ThenFiguresArranged theSelectedFiguresAreInFrontOfTheOthers() {
        List<Figure> viewFigures = editor.getActiveView().getDrawing().getChildren();
        int selection = selectedFigures.size();
        int end = viewFigures.size();
        int start = end - selection;
        
        Set<Figure> frontSet = new HashSet<>(viewFigures.subList(start, end));
        assertFigureContains(frontSet, selectedFigures);
        
        return this;
    }

    private void assertFigureContains(Set<Figure> viewFigures, Set<Figure> expectedFigures) {
        assertEquals(viewFigures.size(), expectedFigures.size());
        for (Figure figure : expectedFigures) {
            assertTrue(viewFigures.contains(figure));
        }
    }
}
