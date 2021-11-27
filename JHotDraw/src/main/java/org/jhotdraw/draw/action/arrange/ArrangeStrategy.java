package org.jhotdraw.draw.action.arrange;

public class ArrangeStrategy {

    public ArrangeLayer redo;
    public ArrangeLayer undo;

    public ArrangeStrategy(ArrangeLayer redo, ArrangeLayer undo) {
        this.redo = redo;
        this.undo = undo;
    }
}
