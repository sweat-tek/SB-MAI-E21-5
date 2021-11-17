package org.jhotdraw.undo;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import static org.junit.Assert.assertTrue;

public class WhenChangeMade extends Stage<WhenChangeMade>{
    @ExpectedScenarioState
    protected UndoRedoManager manager;
    
    @ProvidedScenarioState
    protected Edit edit;

    public WhenChangeMade an_undoable_change_is_made() {
        // The edit is not significant so it should not account as an undoable edit
        manager.addEdit(new Edit(false, true, false));
        assertTrue(!manager.canUndo());
        assertTrue(!manager.canUndoOrRedo());
        
        edit = new Edit(true, true, false);
        
        // Edit is significant, should be undoable
        manager.addEdit(edit);
        assertTrue(manager.canUndo());
        assertTrue(manager.canUndoOrRedo());
        return this;
    }
}
