package org.swing.app.view.components.ui.label;

import org.swing.app.view.common.ViewConstants;

public class ActivationLabel extends Label {

     public ActivationLabel() {
        super("");
        setBackgroundColor(ViewConstants.ACTIVATION_COLOR);
        deactivate();
    }

    public void activate() {
        setOpaque(true);
    }

    public void deactivate() {
        setOpaque(false);
    }
}
