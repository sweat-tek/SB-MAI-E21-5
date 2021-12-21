/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jhotdraw.samples.svg.figures.scenarioTest;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import org.jhotdraw.draw.AttributeKey;
import org.jhotdraw.util.ResourceBundleUtil;

/**
 *
 * @author lucas
 */
public class GivenText extends Stage< GivenText> {
    @ProvidedScenarioState
    protected AttributeKey<String> text;
    private static ResourceBundleUtil labels;

    public GivenText new_text_added(){
        text = new AttributeKey<>("text", String.class, "Text", false, labels);
        return self();
    }
}
