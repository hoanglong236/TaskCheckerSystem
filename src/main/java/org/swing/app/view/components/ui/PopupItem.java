package org.swing.app.view.components.ui;

import org.swing.app.common.ArrayIterator;
import org.swing.app.view.components.SimpleComponent;
import org.swing.app.view.components.factory.JComponentFactory;

import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.Iterator;

public class PopupItem extends SimpleComponent {

    public PopupItem(String itemName) {
        this.component = JComponentFactory.createJMenuItem();
        ((JMenuItem) this.component).setText(itemName);
    }

    public JMenuItem getMenuItem() {
        return (JMenuItem) this.component;
    }

    public void addActionListener(ActionListener actionListener) {
        ((JMenuItem) this.component).addActionListener(actionListener);
    }

    public void removeActionListener(ActionListener actionListener) {
        ((JMenuItem) this.component).removeActionListener(actionListener);
    }

    private ActionListener[] getActionListeners() {
        return  ((JMenuItem) this.component).getActionListeners();
    }

    private void removeActionListeners(ActionListener[] actionListeners) {
        final Iterator<ActionListener> actionListenerIterator = new ArrayIterator<>(actionListeners);

        while (actionListenerIterator.hasNext()) {
            final ActionListener actionListener = actionListenerIterator.next();
            removeActionListener(actionListener);
        }
    }

    private MouseListener[] getMouseListeners() {
        return this.component.getMouseListeners();
    }

    private void removeMouseListeners(MouseListener[] mouseListeners) {
        final Iterator<MouseListener> mouseListenerIterator = new ArrayIterator<>(mouseListeners);

        while (mouseListenerIterator.hasNext()) {
            final MouseListener mouseListener = mouseListenerIterator.next();
            removeMouseListener(mouseListener);
        }
    }

    public void dispose() {
        removeActionListeners(getActionListeners());
        removeMouseListeners(getMouseListeners());
    }
}
