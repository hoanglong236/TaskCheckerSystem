package org.swing.app.view.components.form.components.wrapper;

import org.swing.app.view.components.form.components.factory.InputComponentFactory;
import org.swing.app.view.components.form.components.LabelAndInputWrapper;

public class LabelAndTextFieldWrapper extends LabelAndInputWrapper {

    public LabelAndTextFieldWrapper(String labelText, String initValue) {
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
