package org.jhotdraw.undo;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import static org.junit.Assert.assertTrue;

public class ThenChangeMade extends Stage<ThenChangeMade> {
    @ExpectedScenarioState
    protected UndoRedoManager manager;
    
    @ExpectedScenarioState
    protected Edit edit;
    
    public ThenChangeMade the_change_can_be_undone() {
        assertTrue(manager.getUndoPresentationName().equals(edit.getUndoPresentationName()));
        return this;
    }

    public ThenChangeMade there_are_no_edits() {
        assertTrue(!manager.hasSignificantEdits());
        return this;
    }
}
