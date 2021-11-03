package org.jhotdraw.draw.action.arrange;

import java.util.*;
import javax.swing.undo.*;
import org.jhotdraw.draw.*;
import org.jhotdraw.draw.action.AbstractSelectedAction;

/**
 *
 * @author antje12
 */
public abstract class ArrangeAction extends AbstractSelectedAction {

    public String ID;
    private ArrangeLayer direction;

    /**
     * Creates a new instance.
     */
    // Should only have 2 parameters
    public ArrangeAction(DrawingEditor editor, String id, ArrangeLayer direction) {
        super(editor);
        this.ID = id;
        this.direction = direction;
    }

    public void actionPerformed(java.awt.event.ActionEvent e) {
        ArrangeModel model = new ArrangeModel(getView());

        arrange(model, direction);
        ArrangeStrategy strategy = null;

        if (direction == ArrangeLayer.BACK) {
            strategy = new ArrangeStrategy(ArrangeLayer.BACK, ArrangeLayer.FRONT);
        } else {
            strategy = new ArrangeStrategy(ArrangeLayer.FRONT, ArrangeLayer.BACK);
        }

        UndoableEdit edit = undoableAction(model, strategy);
        fireUndoableEditHappened(edit);
    }

    // Should be shorter, but not possible
    private UndoableEdit undoableAction(ArrangeModel model, ArrangeStrategy strategy) {
        return new AbstractUndoableEdit() {
            @Override
            public String getPresentationName() {
                return labels.getTextProperty(ID);
            }

            @Override
            public void redo() throws CannotRedoException {
                super.redo();
                SendToBackAction.arrange(model, strategy.redo);
            }

            @Override
            public void undo() throws CannotUndoException {
                super.undo();
                BringToFrontAction.arrange(model, strategy.undo);
            }
        };
    }

    public static void arrange(ArrangeModel model, ArrangeLayer direction) {
        Iterator i = model.figures.iterator();
        Drawing drawing = model.view.getDrawing();
        while (i.hasNext()) {
            Figure figure = (Figure) i.next();
            drawing.arrange(figure, direction);
        }
    }
}
