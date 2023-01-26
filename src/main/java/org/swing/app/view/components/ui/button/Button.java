package org.swing.app.view.components.ui.button;

import org.swing.app.common.ArrayIterator;
import org.swing.app.view.components.SimpleComponent;
import org.swing.app.view.util.ViewUtil;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.util.Iterator;

public abstract class Button extends SimpleComponent {

    public void setIcon(String iconLocation, int iconWidth, int iconHeight) {
        final ImageIcon imageIcon = ViewUtil.getImageIcon(iconLocation, iconWidth, iconHeight);
        ((AbstractButton) this.sourceComponent).setIcon(imageIcon);
    }

    public void setSelected(boolean selected) {
        ((AbstractButton) this.sourceComponent).setSelected(selected);
    }

    public boolean isSelected() {
        return ((AbstractButton) this.sourceComponent).isSelected();
    }

    public void setText(String text) {
        ((AbstractButton) this.sourceComponent).setText(text);
    }

    public String getText() {
        return ((AbstractButton) this.sourceComponent).getText();
    }

    public void setFocusOutline(boolean enable) {
        ((AbstractButton) this.sourceComponent).setFocusPainted(enable);
    }

    public void addActionListener(ActionListener actionListener) {
        ((AbstractButton) this.sourceComponent).addActionListener(actionListener);
    }

    public void removeActionListener(ActionListener actionListener) {
        ((AbstractButton) this.sourceComponent).removeActionListener(actionListener);
    }

    private ActionListener[] getActionListeners() {
        return ((AbstractButton) this.sourceComponent).getActionListeners();
    }

    private void removeActionListeners(ActionListener[] actionListeners) {
        final Iterator<ActionListener> actionListenerIterator = new ArrayIterator<>(actionListeners);

        while (actionListenerIterator.hasNext()) {
            final ActionListener actionListener = actionListenerIterator.next();
            removeActionListener(actionListener);
        }
    }

    @Override
    public void cancelAllEventListeners() {
        super.cancelAllEventListeners();
        removeActionListeners(getActionListeners());
    }
}
