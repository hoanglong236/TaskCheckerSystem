package org.swing.app.view.components.ui;

import org.swing.app.common.ArrayIterator;

import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.Iterator;

public class PopupItem {

    private final JMenuItem menuItem;

    public PopupItem(String itemName) {
        this.menuItem = new JMenuItem();
        this.menuItem.setText(itemName);
    }

    public JMenuItem getMenuItem() {
        return this.menuItem;
    }

    public void addActionListener(ActionListener actionListener) {
        this.menuItem.addActionListener(actionListener);
    }

    public void removeActionListener(ActionListener actionListener) {
        this.menuItem.removeActionListener(actionListener);
    }

    private ActionListener[] getActionListeners() {
        return this.menuItem.getActionListeners();
    }

    private void removeActionListeners() {
        final Iterator<ActionListener> actionListenerIterator = new ArrayIterator<>(getActionListeners());

        while (actionListenerIterator.hasNext()) {
            final ActionListener actionListener = actionListenerIterator.next();
            removeActionListener(actionListener);
        }
    }

    public void addMouseListener(MouseListener mouseListener) {
        this.menuItem.addMouseListener(mouseListener);
    }

    public void removeMouseListener(MouseListener mouseListener) {
        this.menuItem.removeMouseListener(mouseListener);
    }

    private MouseListener[] getMouseListeners() {
        return this.menuItem.getMouseListeners();
    }

    private void removeMouseListeners() {
        final Iterator<MouseListener> mouseListenerIterator = new ArrayIterator<>(getMouseListeners());

        while (mouseListenerIterator.hasNext()) {
            final MouseListener mouseListener = mouseListenerIterator.next();
            removeMouseListener(mouseListener);
        }
    }

    public void dispose() {
        removeActionListeners();
        removeMouseListeners();
    }
}
