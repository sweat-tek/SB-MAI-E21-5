package org.jhotdraw.draw.action.arrange;

import java.util.LinkedList;
import org.jhotdraw.draw.DrawingView;
import org.jhotdraw.draw.Figure;

public class ArrangeModel {

    public final DrawingView view;
    public final LinkedList<Figure> figures;

    public ArrangeModel(final DrawingView view) {
        this.view = view;
        this.figures = new LinkedList<Figure>(view.getSelectedFigures());
    }
}
