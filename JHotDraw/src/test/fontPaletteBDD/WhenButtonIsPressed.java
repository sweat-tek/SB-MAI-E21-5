package org.jhotdraw.fontPaletteBDD;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.BeforeStage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import org.jhotdraw.draw.DrawingEditor;
import org.jhotdraw.draw.Figure;
import org.jhotdraw.draw.action.ButtonFactory;
import org.jhotdraw.draw.action.FontChooserHandler;
import org.jhotdraw.gui.JFontChooser;
import org.jhotdraw.util.ResourceBundleUtil;

import java.awt.Font;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JPopupMenu;

import static org.jhotdraw.draw.AttributeKeys.*;
import static org.mockito.Mockito.mock;

public class WhenButtonIsPressed extends Stage<WhenButtonIsPressed> {

    @ExpectedScenarioState
    @ProvidedScenarioState
    private DrawingEditor editor;

    @ProvidedScenarioState
    private Set<Figure> selectedFigures;

    @ProvidedScenarioState
    private Set<Figure> unselectedFigures;

    private FontChooserHandler fontChooserHandler;
    private JButton boldButton;
    private JButton italicButton;
    private JButton underlineButton;
    private JFontChooser fontChooser;
    private ResourceBundleUtil labels;

    @BeforeStage
    public void before() {

        fontChooser = new JFontChooser();

        labels = ResourceBundleUtil.getBundle("org.jhotdraw.draw.Labels");

        fontChooserHandler = new FontChooserHandler(
                editor,
                FONT_FACE,
                fontChooser,
                mock(JPopupMenu.class));

        boldButton = ButtonFactory.createFontStyleBoldButton(editor, labels);
        italicButton = ButtonFactory.createFontStyleItalicButton(editor, labels);
        underlineButton = ButtonFactory.createFontStyleUnderlineButton(editor, labels);

        selectedFigures = new HashSet<>(editor.getActiveView().getSelectedFigures());

        unselectedFigures = new HashSet<>(editor.getActiveView().getDrawing().getChildren());
        unselectedFigures.removeAll(selectedFigures);
    }

    WhenButtonIsPressed fontChangeButtonIsPressed() {
        fontChooser.setSelectedFont(new Font("Arial", Font.PLAIN, 12));
        fontChooserHandler.applySelectedFontToFigures();
        return this;
    }

    WhenButtonIsPressed boldFontButtonIsPressed() {
        boldButton.doClick();
        return this;
    }

    WhenButtonIsPressed italicFontButtonIsPressed() {
        italicButton.doClick();
        return this;
    }

    WhenButtonIsPressed underlineFontButtonIsPressed() {
        underlineButton.doClick();
        return this;
    }
}
