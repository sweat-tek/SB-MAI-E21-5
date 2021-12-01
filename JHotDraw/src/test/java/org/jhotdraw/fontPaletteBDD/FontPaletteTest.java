package org.jhotdraw.fontPaletteBDD;

import com.tngtech.jgiven.junit.ScenarioTest;

import org.junit.Test;

public class FontPaletteTest extends ScenarioTest<GivenSelectedFigures, WhenButtonIsPressed, ThenFontChanged> {

    @Test
    public void selectingFiguresAndPressingButtonToChangeFont() {
        given().aSetOfSelectedFigures();
        when().fontChangeButtonIsPressed();
        then().onlyTheSelectedFiguresHaveChangedFontFace();
    }

    @Test
    public void selectingFiguresAndPressingButtonToMakeFontBold() {
        given().aSetOfSelectedFigures();
        when().boldFontButtonIsPressed();
        then().onlyTheSelectedFiguresAreBold();
    }

    @Test
    public void selectingFiguresAndPressingButtonToMakeFontItalic() {
        given().aSetOfSelectedFigures();
        when().italicFontButtonIsPressed();
        then().onlyTheSelectedFiguresAreItalic();
    }

    @Test
    public void selectingFiguresAndPressingButtonToMakeFontUnderlined() {
        given().aSetOfSelectedFigures();
        when().underlineFontButtonIsPressed();
        then().onlyTheSelectedFiguresAreUnderlined();
    }
}
