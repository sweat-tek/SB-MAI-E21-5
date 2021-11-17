package org.jhotdraw.undo;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import static org.junit.Assert.assertTrue;

public class GivenNoChanges extends Stage<GivenNoChanges> {
    @ProvidedScenarioState
    protected UndoRedoManager manager;

    public GivenNoChanges no_changes() {
        manager = new UndoRedoManager();
        
        assertTrue(!manager.canUndo());
        assertTrue(!manager.canUndoOrRedo());
        return this;
    }
}
