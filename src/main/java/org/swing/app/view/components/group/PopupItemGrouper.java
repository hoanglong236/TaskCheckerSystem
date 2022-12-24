package org.swing.app.view.components.group;

import org.swing.app.view.components.ui.PopupItem;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;

public class PopupItemGrouper {

    private ButtonGroup buttonGroup = new ButtonGroup();

    public void add(PopupItem popupItem) {
        this.buttonGroup.add((AbstractButton) popupItem.getComponent());
    }

    public void remove(PopupItem popupItem) {
        this.buttonGroup.remove((AbstractButton) popupItem.getComponent());
    }

    public void clearSelection() {
        this.buttonGroup.clearSelection();
    }
}
