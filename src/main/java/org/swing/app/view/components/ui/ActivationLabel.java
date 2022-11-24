package org.swing.app.view.components.ui;

import org.swing.app.view.common.ViewConstant;

public class ActivationLabel extends Label {

    public ActivationLabel() {
        super("");
        setBackgroundColor(ViewConstant.ACTIVATION_COLOR);
        deactivate();
    }

    public void activate() {
        setOpaque(true);
    }

    public void deactivate() {
        setOpaque(false);
    }
}
