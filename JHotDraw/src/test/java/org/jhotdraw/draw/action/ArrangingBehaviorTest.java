package org.jhotdraw.draw.action;

import com.tngtech.jgiven.junit.ScenarioTest;
import org.junit.Test;

public class ArrangingBehaviorTest extends ScenarioTest<GivenFiguresToArrange, WhenArranging, ThenFiguresArranged> {

    @Test
    public void selectingFiguresAndArrangingThemResultsInThemStillBeingSelected() {
        given()
                .someSelectedFigures()
                .and()
                .someUnselectedFigures();
        when()
                .sendingFiguresToBack()
                .and()
                .bringingFiguresToFront();
        then()
                .theSelectedFiguresAreStillSelected();
    }

    @Test
    public void selectingFiguresAndSendingThemToTheBackResultsInThemBeingLayeredInTheBack() {
        given()
                .someSelectedFigures()
                .and()
                .someUnselectedFigures();
        when()
                .sendingFiguresToBack();
        then()
                .theSelectedFiguresAreStillSelected()
                .and()
                .theSelectedFiguresAreBehindTheOthers();
    }
    
    @Test
    public void selectingFiguresAndBringingThemToTheFrontResultsInThemBeingLayeredInTheFront() {
        given()
                .someSelectedFigures()
                .and()
                .someUnselectedFigures();
        when()
                .bringingFiguresToFront();
        then()
                .theSelectedFiguresAreStillSelected()
                .and()
                .theSelectedFiguresAreInFrontOfTheOthers();
    }
}
