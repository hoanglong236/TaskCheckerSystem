package org.swing.app.view.components.ui;

import org.swing.app.common.ArrayIterator;
import org.swing.app.view.components.SimpleComponent;
import org.swing.app.view.util.ViewUtil;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Iterator;

public class Button extends SimpleComponent {

    protected Button(String iconLocation, String buttonText) {
        this.component = JCOMPONENT_FACTORY.createJButton();
        setIcon(iconLocation);
        setText(buttonText);
    }

    protected Button(String buttonText) {
        this.component = JCOMPONENT_FACTORY.createJButton();
        setText(buttonText);
    }

    protected void setIcon(String iconLocation) {
        final byte iconWidth = 15;
        final byte iconHeight = 15;
        final ImageIcon imageIcon = ViewUtil.getImageIcon(iconLocation, iconWidth, iconHeight);

        ((JButton) this.component).setIcon(imageIcon);
    }

    protected void setText(String text) {
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
        removeActionListeners(getActionListeners());
        super.dispose();
    }
}
