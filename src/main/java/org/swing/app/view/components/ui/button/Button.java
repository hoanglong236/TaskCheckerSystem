package org.swing.app.view.components.ui.button;

import org.swing.app.common.ArrayIterator;
import org.swing.app.view.components.SimpleComponent;
import org.swing.app.view.util.ViewUtil;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.util.Iterator;

public abstract class Button extends SimpleComponent {

    public void setIcon(String iconLocation) {
        final byte iconWidth = 15;
        final byte iconHeight = 15;
        final ImageIcon imageIcon = ViewUtil.getImageIcon(iconLocation, iconWidth, iconHeight);

        ((AbstractButton) this.component).setIcon(imageIcon);
    }

    public void setSelected(boolean selected) {
        ((AbstractButton) this.component).setSelected(selected);
    }

    public boolean isSelected() {
        return ((AbstractButton) this.component).isSelected();
    }

    public void setText(String text) {
        ((AbstractButton) this.component).setText(text);
    }

    public String getText() {
        return ((AbstractButton) this.component).getText();
    }

    public void addActionListener(ActionListener actionListener) {
        ((AbstractButton) this.component).addActionListener(actionListener);
    }

    public void removeActionListener(ActionListener actionListener) {
        ((AbstractButton) this.component).removeActionListener(actionListener);
    }

    private ActionListener[] getActionListeners() {
        return ((AbstractButton) this.component).getActionListeners();
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
