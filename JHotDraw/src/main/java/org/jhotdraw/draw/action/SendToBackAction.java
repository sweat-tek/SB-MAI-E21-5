/*
 * @(#)SendToBackAction.java  2.0  2008-05-30
 *
 * Copyright (c) 2003-2008 by the original authors of JHotDraw
 * and all its contributors.
 * All rights reserved.
 *
 * The copyright of this software is owned by the authors and  
 * contributors of the JHotDraw project ("the copyright holders").  
 * You may not use, copy or modify this software, except in  
 * accordance with the license agreement you entered into with  
 * the copyright holders. For details see accompanying license terms. 
 */
package org.jhotdraw.draw.action;

import dk.sdu.mmmi.featuretracer.lib.FeatureEntryPoint;
import java.util.*;
import javax.swing.undo.*;
import org.jhotdraw.app.JHotDrawFeatures;
import org.jhotdraw.draw.*;

/**
 * SendToBackAction.
 *
 * @author Werner Randelshofer
 * @version 2.0 2008-05-30 Renamed from MoveToBackAction to SendToBackAction for
 * consistency with the API of Drawing.
 * <br>1.0 24. November 2003 Created.
 */
public class SendToBackAction extends AbstractSelectedAction {

    public static String ID = "edit.sendToBack";
    private ArrangeLayer direction;

    /**
     * Creates a new instance.
     */
    public SendToBackAction(DrawingEditor editor) {
        this(editor, ArrangeLayer.BACK);
        labels.configureAction(this, ID);
    }

    public SendToBackAction(DrawingEditor editor, ArrangeLayer direction) {
        super(editor);
        this.direction = direction;
    }

    @FeatureEntryPoint(JHotDrawFeatures.ARRANGE)
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

    // ToDo [aj] Smell = Write Short Units of Code
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

    public class ArrangeModel {

        private final DrawingView view;
        private final LinkedList<Figure> figures;

        public ArrangeModel(final DrawingView view) {
            this.view = view;
            this.figures = new LinkedList<Figure>(view.getSelectedFigures());
        }
    }

    public class ArrangeStrategy {

        private ArrangeLayer redo;
        private ArrangeLayer undo;

        public ArrangeStrategy(ArrangeLayer redo, ArrangeLayer undo) {
            this.redo = redo;
            this.undo = undo;
        }
    }
}
