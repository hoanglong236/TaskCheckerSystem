package org.swing.app.view.components.ui.button;

import org.swing.app.view.components.factory.JComponentFactory;

public class CheckBox extends Button {

    public CheckBox(String text) {
        this.sourceComponent = JComponentFactory.createJCheckBox();
        setText(text);
    }
}
