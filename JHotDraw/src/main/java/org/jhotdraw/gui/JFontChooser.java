/*
 * @(#)JFontChooser.java  1.0.1  2008-05-25
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
package org.jhotdraw.gui;

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.util.ArrayList;
import java.util.concurrent.*;
import javax.swing.*;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.*;
import org.jhotdraw.gui.fontchooser.*;
import org.jhotdraw.gui.plaf.*;
import org.jhotdraw.gui.plaf.palette.PaletteFontChooserUI;

/**
 * Font chooser dialog.
 *
 * @author Werner Randelshofer
 * @version 1.0.1 2008-05-25 By default, load fonts lazily. Update selection
 * path when the contents of the model changes.
 * <br>1.0 2008-05-18 Created.
 */
public class JFontChooser extends JComponent {

    /**
     * @see #getUIClassID
     * @see #readObject
     */
    private static final String uiClassID = "FontChooserUI";
    /**
     * Identifies the "selectedFont" property.
     */
    public final static String SELECTED_FONT_PROPERTY = "selectedFont";
    /**
     * Identifies the "selectionPath" property.
     */
    public final static String SELECTION_PATH_PROPERTY = "selectionPath";
    /**
     * Instruction to cancel the current selection.
     */
    public static final String CANCEL_SELECTION = "CancelSelection";
    /**
     * Instruction to approve the current selection (same as pressing yes or
     * okay).
     */
    public static final String APPROVE_SELECTION = "ApproveSelection";
    /**
     * Identifies the "model" property.
     */
    public final static String MODEL_PROPERTY = "model";
    /**
     * Holds the selected path of the JFontChooser.
     */
    private TreePath selectionPath;
    /**
     * Holds the selected font of the JFontChooser.
     */
    private Font selectedFont;
    /**
     * Holds the model of the JFontChooser.
     */
    private FontChooserModel model;
    // ********************************
    // ***** Dialog Return Values *****
    // ********************************
    /**
     * Return value if cancel is chosen.
     */
    public static final int CANCEL_OPTION = 1;
    /**
     * Return value if approve (yes, okay) is chosen.
     */
    public static final int APPROVE_OPTION = 0;
    /**
     * Return value if an error occurred.
     */
    public static final int ERROR_OPTION = -1;
    private int returnValue = ERROR_OPTION;
    // DIALOG
    private final JDialog dialog = null;
    /**
     * This future is used to load fonts lazily
     */
    private static FutureTask<Font[]> future;
    private final TreeModelListener modelHandler;

    /**
     * Creates new form JFontChooser
     */
    public JFontChooser() {
        this.modelHandler = new TreeModelListener() {
            
            @Override
            public void treeNodesChanged(TreeModelEvent e) {
                updateSelectionPath(getSelectedFont());
            }
            
            @Override
            public void treeNodesInserted(TreeModelEvent e) {
                updateSelectionPath(getSelectedFont());
            }
            
            @Override
            public void treeNodesRemoved(TreeModelEvent e) {
                updateSelectionPath(getSelectedFont());
            }
            
            @Override
            public void treeStructureChanged(TreeModelEvent e) {
                updateSelectionPath(getSelectedFont());
            }
        };
        loadAllFonts();
        model = new DefaultFontChooserModel.UIResource();
        model.addTreeModelListener(modelHandler);
        updateUI();
        addPropertyChangeListener(new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ("ancestor".equals(evt.getPropertyName()) && evt.getNewValue() != null) {
                    try {
                        ((DefaultFontChooserModel) model).setFonts(getAllFonts());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    JFontChooser.this.removePropertyChangeListener(this);
                }
            }
        });
    }

    /**
     * Resets the UI property with a value from the current look and feel.
     *
     * @see JComponent#updateUI
     */
    @Override
    public void updateUI() {
        // Try to get a browser UI from the UIManager.
        // Fall back to BasicBrowserUI, if none is available.
        if (UIManager.get(getUIClassID()) != null) {
            setUI((FontChooserUI) UIManager.getUI(this));
        } else {
            setUI(PaletteFontChooserUI.createUI(this));
        }
    }

    /**
     * Returns the look and feel (L&F) object that renders this component.
     *
     * @return the PanelUI object that renders this component
     * @since 1.4
     */
    public FontChooserUI getUI() {
        return (FontChooserUI) ui;
    }

    /**
     * Sets the look and feel (L&F) object that renders this component.
     *
     * @param ui the PanelUI L&F object
     * @see UIDefaults#getUI
     */
    public void setUI(FontChooserUI ui) {
        super.setUI(ui);
    }

    /**
     * Returns a string that specifies the name of the L&F class that renders
     * this component.
     *
     * @return "FontChooserUI"
     * @see JComponent#getUIClassID
     * @see UIDefaults#getUI
     */
    @Override
    public String getUIClassID() {
        return uiClassID;
    }

    /**
     * Called by the UI when the user hits the Approve button (labeled "Open" or
     * "Save", by default). This can also be called by the programmer. This
     * method causes an action event to fire with the command string equal to
     * <code>APPROVE_SELECTION</code>.
     *
     * @see #APPROVE_SELECTION
     */
    public void approveSelection() {
        returnValue = APPROVE_OPTION;
        if (dialog != null) {
            dialog.setVisible(false);
        }
        fireActionPerformed(APPROVE_SELECTION);
    }

    /**
     * Called by the UI when the user chooses the Cancel button. This can also
     * be called by the programmer. This method causes an action event to fire
     * with the command string equal to <code>CANCEL_SELECTION</code>.
     *
     * @see #CANCEL_SELECTION
     */
    public void cancelSelection() {
        returnValue = CANCEL_OPTION;
        if (dialog != null) {
            dialog.setVisible(false);
        }
        fireActionPerformed(CANCEL_SELECTION);
    }

    /**
     * Adds an <code>ActionListener</code> to the font chooser.
     *
     * @param l the listener to be added
     *
     * @see #approveSelection
     * @see #cancelSelection
     */
    public void addActionListener(ActionListener l) {
        listenerList.add(ActionListener.class, l);
    }

    /**
     * Removes an <code>ActionListener</code> from the font chooser.
     *
     * @param l the listener to be removed
     *
     * @see #addActionListener
     */
    public void removeActionListener(ActionListener l) {
        listenerList.remove(ActionListener.class, l);
    }

    /**
     * Notifies all listeners that have registered interest for notification on
     * this event type.The event instance is lazily created using the
    <code>command</code> parameter.
     * @param command
     */
    protected void fireActionPerformed(String command) {
        // Guaranteed to return a non-null array
        Object[] listeners = listenerList.getListenerList();
        long mostRecentEventTime = EventQueue.getMostRecentEventTime();
        int modifiers = 0;
        AWTEvent currentEvent = EventQueue.getCurrentEvent();
        if (currentEvent instanceof InputEvent) {
            modifiers = ((InputEvent) currentEvent).getModifiers();
        } else if (currentEvent instanceof ActionEvent) {
            modifiers = ((ActionEvent) currentEvent).getModifiers();
        }
        ActionEvent e = null;
        // Process the listeners last to first, notifying
        // those that are interested in this event
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == ActionListener.class) {
                // Lazily create the event:
                if (e == null) {
                    e = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                            command, mostRecentEventTime,
                            modifiers);
                }
                ((ActionListener) listeners[i + 1]).actionPerformed(e);
            }
        }
    }

    /**
     * Gets the selected Font. This is a bound property.
     *
     * @return The selected font, or null, if no font is selected.
     */
    public TreePath getSelectionPath() {
        return selectionPath;
    }

    /**
     * Sets the selected Font. This is a bound property.
     * <p>
     * Changing the selection path, causes a change of the selected font, if the
     * selected font is not the last path segment of the selection path.
     *
     * @param newValue The new selected font, or null if no font is to be
     * selected..
     */
    public void setSelectionPath(TreePath newValue) {
        TreePath oldValue = selectionPath;
        this.selectionPath = newValue;
        firePropertyChange(SELECTION_PATH_PROPERTY, oldValue, newValue);
        if (selectionPath != null && selectionPath.getPathCount() == 4) {
            setSelectedFont(((FontFaceNode) selectionPath.getLastPathComponent()).getFont());
        }
    }

    /**
     * Starts loading all fonts from the local graphics environment using a
     * worker thread.
     */
    public synchronized static void loadAllFonts() {
        if (future == null) {
            future = new FutureTask<Font[]>(() -> {
                Font[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
                
                // get rid of bogus fonts
                ArrayList<Font> goodFonts = new ArrayList<Font>(fonts.length);
                for (Font f : fonts) {
                    Font decoded = Font.decode(f.getFontName());
                    if (decoded.getFontName().equals(f.getFontName()) || decoded.getFontName().endsWith("-Derived")) {
                        goodFonts.add(f);
                    }
                }
                return goodFonts.toArray(new Font[goodFonts.size()]);
            });
            new Thread(future).start();
        }
    }

    /**
     * Gets all fonts from the graphics environment. This may take a long time.
     * It is recommended to call loadAllFonts during the startup of an
     * application. If you do this, you can retrieve the fonts from this method
     * from the AWT Event Dispatcher Thread.
     *
     * @return All fonts.
     */
    public static synchronized Font[] getAllFonts() {
        loadAllFonts();
        try {
            return future.get().clone();
        } catch (InterruptedException | ExecutionException ex) {
            return new Font[0];
        }
    }

    /**
     * Gets the selected Font. This is a bound property.
     *
     * @return The selected font, or null, if no font is selected.
     */
    public Font getSelectedFont() {
        return selectedFont;
    }

    /**
     * Sets the selected Font.
     * <p>
     * Changing the selected font, causes a change of the selection path, if the
     * selected font is not the last path segment of the selection path.
     *
     * This is a bound property.
     *
     * @param newValue The new selected font, or null if no font is to be
     * selected.
     */
    public void setSelectedFont(Font newValue) {
        Font oldValue = selectedFont;
        this.selectedFont = newValue;
        firePropertyChange(SELECTED_FONT_PROPERTY, oldValue, newValue);
        updateSelectionPath(newValue);
    }

    /**
     * Updates the selection path to the selected font.
     * <p>
     * This method is invoked, when a font is selected, and when then structure
     * of the model has changed.
     *
     * @param newValue
     */
    protected void updateSelectionPath(Font newValue) {
        if (newValue == null || selectionPath == null || selectionPath.getPathCount() != 4
                || !((FontFaceNode) selectionPath.getLastPathComponent()).getFont().getFontName().equals(newValue.getFontName())) {
            if (newValue == null) {
                setSelectionPath(null);
            } else {
                TreePath path = selectionPath;
                FontCollectionNode fontCollection = (path != null && path.getPathCount() > 1) ? (FontCollectionNode) path.getPathComponent(1) : null;;
                FontFamilyNode fontFamily = (path != null && path.getPathCount() > 2) ? (FontFamilyNode) path.getPathComponent(2) : null;;
                FontFaceNode fontFace = null;

                if (fontFace == null && fontFamily != null) {
                    searchCurrentFamily(newValue, fontFace, fontFamily);
                }

                if (fontFace == null && fontCollection != null) {
                    searchCurrentCollection(newValue, fontFace, fontFamily, fontCollection);
                }

                if (fontFace == null) {
                    searchAllCollections(newValue, fontFace, fontFamily, fontCollection);
                }

                if (fontFace != null) {
                    setSelectionPath(new TreePath(new Object[]{
                        getModel().getRoot(), fontCollection, fontFamily, fontFace
                    }));
                } else {
                    setSelectionPath(null);
                }
            }
        }
    }
    
    /**
     * Searches the current font family for the new font
     *
     * @param newValue
     * @param fontFace
     * @param fontFamily
     */
    protected void searchCurrentFamily(Font newValue, FontFaceNode fontFace, FontFamilyNode fontFamily) {
        for (FontFaceNode face : fontFamily.faces()) {
            if (face.getFont().getFontName().equals(newValue.getFontName())) {
                fontFace = face;
                return;
            }
        }
    }

    /**
     * Searches the current font collection for the new font
     *
     * @param newValue
     * @param fontFace
     * @param fontFamily
     * @param fontCollection
     */
    protected void searchCurrentCollection(Font newValue, FontFaceNode fontFace, FontFamilyNode fontFamily, FontCollectionNode fontCollection) {
        for (FontFamilyNode family : fontCollection.families()) {
            for (FontFaceNode face : family.faces()) {
                if (face.getFont().getFontName().equals(newValue.getFontName())) {
                    fontFamily = family;
                    fontFace = face;
                    return;
                }
            }
        }
    }

    /**
     * Searches all the font collections for the new font
     *
     * @param newValue
     * @param fontFace
     * @param fontFamily
     * @param fontCollection
     */
    protected void searchAllCollections(Font newValue, FontFaceNode fontFace, FontFamilyNode fontFamily, FontCollectionNode fontCollection) {
        TreeNode root = (TreeNode) getModel().getRoot();
        for (int i = 0, n = root.getChildCount(); i < n; i++) {
            FontCollectionNode collection = (FontCollectionNode) root.getChildAt(i);
            for (FontFamilyNode family : collection.families()) {
                for (FontFaceNode face : family.faces()) {
                    if (face.getFont().getFontName().equals(newValue.getFontName())) {
                        fontCollection = collection;
                        fontFamily = family;
                        fontFace = face;
                        return;
                    }
                }
            }
        }
    }

    /**
     * Gets the selected Font. This is a bound property.
     *
     * @return The selected font, or null, if no font is selected.
     */
    public FontChooserModel getModel() {
        return model;
    }

    /**
     * Sets the selected Font. This is a bound property.
     *
     * @param newValue The new selected font, or null if no font is to be
     * selected..
     */
    public void setModel(FontChooserModel newValue) {
        FontChooserModel oldValue = model;
        if (oldValue != null) {
            oldValue.removeTreeModelListener(modelHandler);
        }
        this.model = newValue;
        if (newValue != null) {
            newValue.addTreeModelListener(modelHandler);
        }
        firePropertyChange(MODEL_PROPERTY, oldValue, newValue);
        updateSelectionPath(selectedFont);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor. /
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
    }// </editor-fold>//GEN-END:initComponents
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
