package org.swing.app.view.components.form.components.input;

import org.swing.app.view.components.factory.JComponentFactory;


public class TextField extends AllowTypingInputComponent {

    public TextField() {
        super();
        this.sourceComponent = JComponentFactory.createJTextField();
    }
}
