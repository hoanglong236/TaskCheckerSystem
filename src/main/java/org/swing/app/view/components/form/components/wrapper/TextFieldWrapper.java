package org.swing.app.view.components.form.components.wrapper;

import org.swing.app.view.components.form.components.InputComponentWrapper;
import org.swing.app.view.components.form.components.factory.InputComponentFactory;

public class TextFieldWrapper extends InputComponentWrapper<String> {

    public TextFieldWrapper(String labelText, String initValue) {
        super();
        init(labelText, initValue);
    }

    private void initInputField(String initValue) {
        this.inputField = InputComponentFactory.createTextField(initValue);
    }

    private void init(String labelText, String initValue) {
        initLabelField(labelText);
        addChildComponent(this.labelField);

        initInputField(initValue);
        addChildComponent(this.inputField);
    }
}
