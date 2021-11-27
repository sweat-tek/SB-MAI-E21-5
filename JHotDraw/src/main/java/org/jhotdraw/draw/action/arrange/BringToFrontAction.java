package org.jhotdraw.draw.action.arrange;

import dk.sdu.mmmi.featuretracer.lib.FeatureEntryPoint;
import org.jhotdraw.app.JHotDrawFeatures;
import org.jhotdraw.draw.*;

public class BringToFrontAction extends ArrangeAction {
    public final static String ID = "edit.bringToFront";

    @FeatureEntryPoint(JHotDrawFeatures.ARRANGE)
    public BringToFrontAction(DrawingEditor editor) {
        super(editor, ID, ArrangeLayer.FRONT);
        labels.configureAction(this, ID);
    }
}
