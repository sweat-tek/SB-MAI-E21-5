package org.jhotdraw.draw.action.arrange;

import dk.sdu.mmmi.featuretracer.lib.FeatureEntryPoint;
import org.jhotdraw.app.JHotDrawFeatures;
import org.jhotdraw.draw.*;

public class SendToBackAction extends ArrangeAction {
    public final static String ID = "edit.sendToBack";

    @FeatureEntryPoint(JHotDrawFeatures.ARRANGE)
    public SendToBackAction(DrawingEditor editor) {
        super(editor, ID, ArrangeLayer.BACK);
        labels.configureAction(this, ID);
    }
}
