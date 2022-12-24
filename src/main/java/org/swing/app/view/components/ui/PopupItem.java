package org.swing.app.view.components.ui;

import org.swing.app.common.ArrayIterator;
import org.swing.app.view.components.SimpleComponent;
import org.swing.app.view.components.factory.JComponentFactory;

import javax.swing.JComponent;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.Iterator;

public class PopupItem extends SimpleComponent {

    public static final byte NORMAL_TYPE_POPUP_ITEM = 0;
    public static final byte RADIO_BUTTON_TYPE_POPUP_ITEM = 1;
    public static final byte CHECK_BOX_TYPE_POPUP_ITEM = 2;

    public PopupItem(byte type, String itemText) {
        super();
        this.component = getPopupItemComponentByType(type);
        setText(itemText);
    }

    private JComponent getPopupItemComponentByType(byte type) {
        switch (type) {
            case NORMAL_TYPE_POPUP_ITEM:
                return JComponentFactory.createJMenuItem();
            case RADIO_BUTTON_TYPE_POPUP_ITEM:
                return JComponentFactory.createJRadioButtonMenuItem();
            case CHECK_BOX_TYPE_POPUP_ITEM:
                return JComponentFactory.createJCheckBoxMenuItem();
            default:
                throw new IllegalArgumentException();
        }
    }

    public void setText(String text) {
        ((JMenuItem) this.component).setText(text);
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
