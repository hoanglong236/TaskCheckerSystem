package org.swing.app.view.components.form.components.wrapper;

import org.swing.app.view.components.form.components.factory.InputComponentFactory;

public class TextFieldWrapper extends SingleInputComponentWrapper<String> {

    public TextFieldWrapper(String labelText, String initValue) {
        super();
        init(labelText);
        setValue(initValue);
    }

    private void initInputField() {
        this.inputField = InputComponentFactory.createTextField();
    }

    private void init(String labelText) {
        initLabelField(labelText);
        addChildComponent(this.labelField);

        initInputField();
        addChildComponent(this.inputField);
    }
}
