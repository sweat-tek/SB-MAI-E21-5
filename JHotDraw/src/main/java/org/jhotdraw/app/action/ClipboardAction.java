/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jhotdraw.app.action;

import dk.sdu.mmmi.featuretracer.lib.FeatureEntryPoint;
import java.awt.*;
import javax.swing.*;
import org.jhotdraw.app.JHotDrawFeatures;
/**
 *
 * @author lasse
 */
public abstract class ClipboardAction extends AbstractAction {
    
    @FeatureEntryPoint(JHotDrawFeatures.BASIC_EDITING)
    protected void copy(boolean move) {
        
        Component focusOwner = KeyboardFocusManager.
                getCurrentKeyboardFocusManager().
                getPermanentFocusOwner();
        if (focusOwner != null && focusOwner instanceof JComponent) {
            JComponent component = (JComponent) focusOwner;
            if(move) {
                component.getTransferHandler().exportToClipboard(
                    component,
                    component.getToolkit().getSystemClipboard(),
                    TransferHandler.MOVE
                    );
            } else {
                component.getTransferHandler().exportToClipboard(
                    component,
                    component.getToolkit().getSystemClipboard(),
                    TransferHandler.COPY
                    );
            }
        }
    }
}
