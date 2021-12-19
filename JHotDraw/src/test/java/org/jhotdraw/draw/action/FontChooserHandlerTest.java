package org.jhotdraw.draw.action;

import javax.swing.JPopupMenu;
import java.awt.*;

import org.jhotdraw.draw.DefaultDrawingEditor;
import org.jhotdraw.draw.Figure;
import org.jhotdraw.gui.JFontChooser;
import org.jhotdraw.samples.svg.figures.SVGTextFigure;

import static org.jhotdraw.draw.AttributeKeys.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/**
 *
 * @author Anton Vibæk
 */
public class FontChooserHandlerTest {

    JFontChooser fontChooser;
    FontChooserHandler fontChooserHandler;

    public FontChooserHandlerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        fontChooser = new JFontChooser();
        fontChooserHandler = new FontChooserHandler(
                mock(DefaultDrawingEditor.class),
                FONT_FACE,
                fontChooser,
                mock(JPopupMenu.class));
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testApplyFontToFigure() {

        Figure figure = new SVGTextFigure();
        assertEquals("VERDANA", FONT_FACE.get(figure).getName());

        fontChooserHandler.applyFontToFigure(figure, new Font("Times New Roman", Font.PLAIN, 12));
        assertEquals("Times New Roman", FONT_FACE.get(figure).getName());

        fontChooserHandler.applyFontToFigure(figure, new Font("Arial", Font.PLAIN, 12));
        assertEquals("Arial", FONT_FACE.get(figure).getName());

    }
}
