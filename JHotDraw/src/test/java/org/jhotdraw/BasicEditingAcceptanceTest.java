/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jhotdraw;

import com.tngtech.jgiven.junit.ScenarioTest;
import org.junit.Test;

/**
 *
 * @author lasse
 */
public class BasicEditingAcceptanceTest extends ScenarioTest<GivenFigures, WhenEditing, ThenChanges> {
    
    @Test
    public void duplicatingAFigure() {
        given().oneSelectedFigure()
                .and().oneUnselectedFigure();
        when().duplicatingFigures();
        then().onlySelectedFiguresAreDuplicated();
    }
    
    @Test
    public void deletingAFigure() {
        given().oneSelectedFigure()
                .and().oneUnselectedFigure();
        when().deletingFigures();
        then().onlySelectedFiguresAreDeleted();
    }
    
    @Test
    public void duplicatingFigures() {
        given().twoSelectedFigures()
                .and().twoUnselectedFigures();
        when().duplicatingFigures();
        then().onlySelectedFiguresAreDuplicated();
    }
    
    @Test
    public void deletingFigures() {
        given().twoSelectedFigures()
                .and().twoUnselectedFigures();
        when().deletingFigures();
        then().onlySelectedFiguresAreDeleted();
    }
}
