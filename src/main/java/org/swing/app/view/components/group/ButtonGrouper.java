package org.swing.app.view.components.group;

import org.swing.app.view.components.ui.button.Button;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;

public class ButtonGrouper {

    private final ButtonGroup buttonGroup;

    public ButtonGrouper() {
        this.buttonGroup = new ButtonGroup();
    }

    public void group(Button button) {
        this.buttonGroup.add((AbstractButton) button.getComponent());
    }

    public void ungroup(Button button) {
        this.buttonGroup.remove((AbstractButton) button.getComponent());
    }

    public void clearSelection() {
        this.buttonGroup.clearSelection();
    }
}
