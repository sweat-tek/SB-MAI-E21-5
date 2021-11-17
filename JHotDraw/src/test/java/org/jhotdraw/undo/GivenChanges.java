package org.jhotdraw.undo;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import static org.junit.Assert.assertTrue;

public class GivenChanges extends Stage<GivenChanges> {
    @ProvidedScenarioState
    protected UndoRedoManager manager;
    
    @ProvidedScenarioState
    protected Edit edit;

    public GivenChanges no_changes() {
        manager = new UndoRedoManager();
        
        assertTrue(!manager.canUndo());
        assertTrue(!manager.canUndoOrRedo());
        return this;
    }

    public GivenChanges an_undoable_change() {
        manager = new UndoRedoManager();
        edit = new Edit(true, true, false);
        manager.addEdit(edit);
        return this;
    }
}
