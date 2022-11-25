package org.swing.app.view.components.form.components.factory.wrapper;

import org.swing.app.view.components.form.components.InputAndLabelWrapper;
import org.swing.app.view.components.form.components.factory.input.InputComponentFactory;

class TextFieldAndLabelWrapper extends InputAndLabelWrapper {

    public TextFieldAndLabelWrapper(String labelText, String initValue) {
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
