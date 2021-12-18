package org.jhotdraw.fontPaletteBDD;

import java.util.Set;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import static org.jhotdraw.draw.AttributeKeys.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.jhotdraw.draw.DrawingEditor;
import org.jhotdraw.draw.Figure;

public class ThenFontChanged extends Stage<ThenFontChanged> {

    @ExpectedScenarioState
    private DrawingEditor editor;

    @ExpectedScenarioState
    private Set<Figure> selectedFigures;

    @ExpectedScenarioState
    private Set<Figure> unselectedFigures;

    ThenFontChanged onlyTheSelectedFiguresHaveChangedFontFace() {

        for (Figure figure : selectedFigures) {
            assertEquals("Arial", FONT_FACE.get(figure).getName());
        }

        for (Figure figure : unselectedFigures) {
            assertNotEquals("Arial", FONT_FACE.get(figure).getName());
        }

        return this;
    }

    ThenFontChanged onlyTheSelectedFiguresAreBold() {

        for (Figure figure : selectedFigures) {
            assertEquals(true, FONT_BOLD.get(figure));
        }

        for (Figure figure : unselectedFigures) {
            assertEquals(false, FONT_BOLD.get(figure));
        }

        return this;
    }

    ThenFontChanged onlyTheSelectedFiguresAreItalic() {

        for (Figure figure : selectedFigures) {
            assertEquals(true, FONT_ITALIC.get(figure));
        }

        for (Figure figure : unselectedFigures) {
            assertEquals(false, FONT_ITALIC.get(figure));
        }

        return this;
    }

    ThenFontChanged onlyTheSelectedFiguresAreUnderlined() {

        for (Figure figure : selectedFigures) {
            assertEquals(true, FONT_UNDERLINE.get(figure));
        }

        for (Figure figure : unselectedFigures) {
            assertEquals(false, FONT_UNDERLINE.get(figure));
        }

        return this;
    }
}
