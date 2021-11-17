package org.jhotdraw.undo;

import com.tngtech.jgiven.junit.ScenarioTest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class UndoRedoManagerTest extends ScenarioTest<GivenNoChanges, WhenChangeMade, ThenChangeMade> {   
    @Test
    public void testCanUndo() {
        given().no_changes();
        when().an_undoable_change_is_made();
        then().the_change_can_be_undone();
    }
    
    @Test
    public void testEditLimit() {
        UndoRedoManager instance = new UndoRedoManager();
        assertEquals(100, instance.getLimit());
    }

    @Test
    public void testSetSignificantEdits() {
        UndoRedoManager instance = new UndoRedoManager();
        // Defaults to false
        assertTrue(!instance.hasSignificantEdits());
        
        instance.setHasSignificantEdits(true);
        assertTrue(instance.hasSignificantEdits());
        
        instance.setHasSignificantEdits(false);
        assertTrue(!instance.hasSignificantEdits());
    }
    
    @Test
    public void testActionsDisabled() {
        UndoRedoManager instance = new UndoRedoManager();
        assertTrue(!instance.getUndoAction().isEnabled());
        assertTrue(!instance.getRedoAction().isEnabled());
    }
    
    @Test
    public void testAddSignificantEdit() {
        UndoRedoManager instance = new UndoRedoManager();
        assertTrue(!instance.hasSignificantEdits());
        
        instance.addEdit(new Edit(false, false, false));
        assertTrue(!instance.hasSignificantEdits());
        
        instance.addEdit(new Edit(true, false, false));
        assertTrue(instance.hasSignificantEdits());
    }
    
    @Test
    public void testDiscardEdits() {
        UndoRedoManager instance = new UndoRedoManager();
        assertTrue(!instance.hasSignificantEdits());
        
        instance.addEdit(new Edit(true, false, false));
        assertTrue(instance.hasSignificantEdits());
        
        instance.discardAllEdits();
        assertTrue(!instance.hasSignificantEdits());
    }
    
    @Test
    public void testPresentationName() {
        UndoRedoManager instance = new UndoRedoManager();
        
        assertTrue(instance.getPresentationName().equals(""));
        
        Edit edit = new Edit(true, true, false);
        instance.addEdit(edit);
        assertTrue(instance.getUndoPresentationName().equals(edit.getUndoPresentationName()));
    }
}
