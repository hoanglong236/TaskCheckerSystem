package org.swing.app.view.components.ui.button;

import org.swing.app.view.components.factory.JComponentFactory;

public class BasicButton extends Button {

    public BasicButton(String text) {
        this.component = JComponentFactory.createJButton();
        setText(text);
    }
}
