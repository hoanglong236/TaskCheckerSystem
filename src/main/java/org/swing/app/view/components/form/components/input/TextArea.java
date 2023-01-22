package org.swing.app.view.components.form.components.input;

import org.swing.app.view.components.factory.JComponentFactory;

public class TextArea extends AllowTypingInputComponent {

    public TextArea() {
        super();
        this.sourceComponent = JComponentFactory.createJTextArea();
        setDefaultValue();
    }
}
