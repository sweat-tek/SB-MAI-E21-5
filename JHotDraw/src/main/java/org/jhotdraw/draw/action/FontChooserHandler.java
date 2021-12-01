/**
 * @(#)FontChooserHandler.java  1.0  22.05.2008
 *
 * Copyright (c) 2008 by the original authors of JHotDraw
 * and all its contributors.
 * All rights reserved.
 *
 * The copyright of this software is owned by the authors and  
 * contributors of the JHotDraw project ("the copyright holders").  
 * You may not use, copy or modify this software, except in  
 * accordance with the license agreement you entered into with  
 * the copyright holders. For details see accompanying license terms. 
 */
package org.jhotdraw.draw.action;

import javax.swing.undo.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.beans.*;
import org.jhotdraw.draw.*;
import org.jhotdraw.gui.JFontChooser;

/**
 * FontChooserHandler.
 *
 * @author Werner Randelshofer  @version 1.0 22.05.2008 Created.
 */
public class FontChooserHandler extends AbstractSelectedAction implements PropertyChangeListener {

    protected AttributeKey<Font> key;
    protected JFontChooser fontChooser;
    protected JPopupMenu popupMenu;
    protected int isUpdating;

    /**
     * Creates a new instance.
     * 
     * @param editor
     * @param key
     * @param fontChooser
     * @param popupMenu
     */
    public FontChooserHandler(DrawingEditor editor, AttributeKey<Font> key, JFontChooser fontChooser,
            JPopupMenu popupMenu) {
        super(editor);
        this.key = key;
        this.fontChooser = fontChooser;
        this.popupMenu = popupMenu;

        fontChooser.addActionListener(this);
        fontChooser.addPropertyChangeListener(this);
        updateEnabledState();
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getActionCommand().equals(JFontChooser.APPROVE_SELECTION)) {
            applySelectedFontToFigures();
        } else if (evt.getActionCommand().equals(JFontChooser.CANCEL_SELECTION)) {
            // Do not do anything
        }
        popupMenu.setVisible(false);
    }

    /**
     * Sets the selected font to the font of the selected figures
     */
    public void applySelectedFontToFigures() {

        final ArrayList<Figure> selectedFigures = new ArrayList<Figure>(getView().getSelectedFigures());
        final ArrayList<Object> restoreData = new ArrayList<Object>(selectedFigures.size());

        for (Figure figure : selectedFigures) {
            restoreData.add(figure.getAttributesRestoreData());
            applyFontToFigure(figure, fontChooser.getSelectedFont());
        }
        getEditor().setDefaultAttribute(key, fontChooser.getSelectedFont());
        final Font undoValue = fontChooser.getSelectedFont();
        UndoableEdit edit = createUndoableEdit(undoValue, selectedFigures, restoreData);
        fireUndoableEditHappened(edit);
    }

    /**
     * Sets the font of the figure to the specified font.
     * 
     * @param figure
     * @param font
     */
    public void applyFontToFigure(Figure figure, Font font) {
        key.set(figure, font);
    }

    /**
     * Creates an object to implement undo and redo functionality
     * 
     * @param undoValue
     * @param selectedFigures
     * @param restoreData
     * @return
     */
    private AbstractUndoableEdit createUndoableEdit(Font undoValue, ArrayList<Figure> selectedFigures,
            ArrayList<Object> restoreData) {
        return new AbstractUndoableEdit() {

            @Override
            public String getPresentationName() {
                return AttributeKeys.FONT_FACE.getPresentationName();
            }

            @Override
            public void undo() {
                super.undo();
                Iterator<Object> iRestore = restoreData.iterator();
                for (Figure figure : selectedFigures) {
                    figure.willChange();
                    figure.restoreAttributesTo(iRestore.next());
                    figure.changed();
                }
            }

            @Override
            public void redo() {
                super.redo();
                for (Figure figure : selectedFigures) {
                    restoreData.add(figure.getAttributesRestoreData());
                    figure.willChange();
                    key.basicSet(figure, undoValue);
                    figure.changed();
                }
            }
        };
    }

    @Override
    protected void updateEnabledState() {
        setEnabled(getEditor().isEnabled());
        if (getView() != null && fontChooser != null && popupMenu != null) {
            fontChooser.setEnabled(getView().getSelectionCount() > 0);
            popupMenu.setEnabled(getView().getSelectionCount() > 0);
            isUpdating++;
            if (getView().getSelectionCount() > 0) {
                for (Figure f : getView().getSelectedFigures()) {
                    if (f instanceof TextHolderFigure) {
                        TextHolderFigure thf = (TextHolderFigure) f;
                        fontChooser.setSelectedFont(thf.getFont());
                        break;
                    }
                }
            }
            isUpdating--;
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (isUpdating++ == 0) {
            if (evt.getPropertyName().equals(JFontChooser.SELECTED_FONT_PROPERTY)) {
                applySelectedFontToFigures();
            }
        }
        isUpdating--;
    }
}
