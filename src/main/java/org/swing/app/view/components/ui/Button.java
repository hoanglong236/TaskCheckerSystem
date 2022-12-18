package org.swing.app.view.components.ui;

import org.swing.app.common.ArrayIterator;
import org.swing.app.view.components.SimpleComponent;
import org.swing.app.view.components.factory.JComponentFactory;
import org.swing.app.view.util.ViewUtil;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Iterator;

public class Button extends SimpleComponent {

    public Button(String text, String iconLocation) {
        this.component = JComponentFactory.createJButton();
        setText(text);
        setIcon(iconLocation);
    }

    public Button(String text) {
        this.component = JComponentFactory.createJButton();
        setText(text);
    }

    public void setIcon(String iconLocation) {
        final byte iconWidth = 15;
        final byte iconHeight = 15;
        final ImageIcon imageIcon = ViewUtil.getImageIcon(iconLocation, iconWidth, iconHeight);

        ((JButton) this.component).setIcon(imageIcon);
    }

    public void setText(String text) {
        ((JButton) this.component).setText(text);
    }

    public void addActionListener(ActionListener actionListener) {
        ((JButton) this.component).addActionListener(actionListener);
    }

    public void removeActionListener(ActionListener actionListener) {
        ((JButton) this.component).removeActionListener(actionListener);
    }

    private ActionListener[] getActionListeners() {
        return ((JButton) this.component).getActionListeners();
    }

    private void removeActionListeners(ActionListener[] actionListeners) {
        final Iterator<ActionListener> actionListenerIterator = new ArrayIterator<>(actionListeners);

        while (actionListenerIterator.hasNext()) {
            final ActionListener actionListener = actionListenerIterator.next();
            removeActionListener(actionListener);
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        removeActionListeners(getActionListeners());
    }
}
