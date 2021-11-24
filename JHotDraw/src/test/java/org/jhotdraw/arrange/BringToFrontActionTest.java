package org.jhotdraw.arrange;

import java.util.HashSet;
import javax.swing.undo.UndoableEdit;
import org.junit.*;
import org.jhotdraw.draw.ArrangeLayer;
import org.jhotdraw.draw.Drawing;
import org.jhotdraw.draw.DrawingEditor;
import org.jhotdraw.draw.DrawingView;
import org.jhotdraw.draw.Figure;
import org.jhotdraw.draw.action.arrange.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;

/**
 *
 * @author antje12
 */
public class BringToFrontActionTest {

    public BringToFrontActionTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testConstructor() {
        BringToFrontAction sut = new BringToFrontAction(null);
        assert (sut.ID == "edit.bringToFront");
        assert (sut.direction == ArrangeLayer.FRONT);
    }

    @Test
    public void testAction() {
        DrawingEditor editor = mock(DrawingEditor.class);
        DrawingView view = mock(DrawingView.class);
        Drawing drawing = mock(Drawing.class);

        Figure figure = mock(Figure.class);
        HashSet<Figure> figures = new HashSet<Figure>();
        figures.add(figure);

        when(editor.getActiveView()).thenReturn(view);
        when(view.getDrawing()).thenReturn(drawing);
        when(view.getSelectedFigures()).thenReturn(figures);
        doNothing().when(drawing).arrange(figure, ArrangeLayer.FRONT);

        BringToFrontAction sut = new BringToFrontAction(editor);
        sut.actionPerformed(null);

        verify(drawing).arrange(figure, ArrangeLayer.FRONT);
        verify(drawing).fireUndoableEditHappened(any(UndoableEdit.class));
    }
}
