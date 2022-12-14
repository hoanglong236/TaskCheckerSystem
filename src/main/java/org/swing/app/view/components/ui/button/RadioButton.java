package org.swing.app.view.components.ui.button;

import org.swing.app.view.components.factory.JComponentFactory;

public class RadioButton extends Button {

    public RadioButton(String text) {
        this.sourceComponent = JComponentFactory.createJRadioButton();
        setText(text);
    }
}
