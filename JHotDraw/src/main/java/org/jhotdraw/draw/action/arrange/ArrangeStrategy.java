package org.jhotdraw.draw.action.arrange;

import org.jhotdraw.draw.ArrangeLayer;

/**
 *
 * @author antje12
 */
public class ArrangeStrategy {

    public ArrangeLayer redo;
    public ArrangeLayer undo;

    public ArrangeStrategy(ArrangeLayer redo, ArrangeLayer undo) {
        this.redo = redo;
        this.undo = undo;
    }
}
