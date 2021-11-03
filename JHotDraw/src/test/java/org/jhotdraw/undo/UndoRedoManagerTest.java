package org.jhotdraw.undo;

import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoableEdit;
import org.junit.Test;
import static org.junit.Assert.*;

class Edit implements UndoableEdit {
    private boolean isSignificant;
    private boolean canUndo;
    private boolean canRedo;
    
    public Edit(boolean isSignificant, boolean canUndo, boolean canRedo) {
        super();
        
        this.isSignificant = isSignificant;
        this.canUndo = canUndo;
        this.canRedo = canRedo;
    }
    
    @Override
    public void undo() throws CannotUndoException {}

    @Override
    public boolean canUndo() {
        return this.canUndo;
    }

    @Override
    public void redo() throws CannotRedoException {}

    @Override
    public boolean canRedo() {
        return this.canRedo;
    }

    @Override
    public void die() {}

    @Override
    public boolean addEdit(UndoableEdit anEdit) {
        return false;
    }

    @Override
    public boolean replaceEdit(UndoableEdit anEdit) {
        return false;
    }

    @Override
    public boolean isSignificant() {
        return this.isSignificant;
    }

    @Override
    public String getPresentationName() {
        return "presentation name";
    }

    @Override
    public String getUndoPresentationName() {
        return "undo presentation name";
    }

    @Override
    public String getRedoPresentationName() {
        return "redo presentation name";
    }   
}

public class UndoRedoManagerTest {   
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
    public void testCanUndo() {
        UndoRedoManager instance = new UndoRedoManager();
        assertTrue(!instance.canUndo());
        assertTrue(!instance.canUndoOrRedo());
        
        // The edit is not significant so it should not account as an undoable edit
        instance.addEdit(new Edit(false, true, false));
        assertTrue(!instance.canUndo());
        assertTrue(!instance.canUndoOrRedo());
        
        Edit edit = new Edit(true, true, false);
        
        // Edit is significant, should be undoable
        instance.addEdit(edit);
        assertTrue(instance.canUndo());
        assertTrue(instance.canUndoOrRedo());
        
        assertTrue(instance.getUndoPresentationName().equals(edit.getUndoPresentationName()));
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
