package org.swing.app.view.components.ui;

import org.swing.app.view.common.ViewConstant;

public class ActivationLabel extends Label {

    public ActivationLabel() {
        super("");
        this.component.setBackground(ViewConstant.ACTIVATION_COLOR);
        deactivate();
    }

    public void activate() {
        this.component.setOpaque(true);
    }

    public void deactivate() {
        this.component.setOpaque(false);
    }
}
