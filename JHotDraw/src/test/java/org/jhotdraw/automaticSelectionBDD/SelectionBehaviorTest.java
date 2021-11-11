/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jhotdraw.automaticSelectionBDD;

import com.tngtech.jgiven.junit.ScenarioTest;
import org.junit.Test;

/**
 *
 * @author svane
 */
public class SelectionBehaviorTest extends ScenarioTest<GivenFiguresDrawn, WhenFiguresSelected, ThenFiguresSelected> {
    
    @Test
    public void automaticSelection() {
        given().figuresDrawn();
        when().figuresSelected();
        then().FiguresAreSelected();
    }
}
