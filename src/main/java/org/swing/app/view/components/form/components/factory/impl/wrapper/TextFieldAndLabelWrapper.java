package org.swing.app.view.components.form.components.factory.impl.wrapper;

import org.swing.app.view.components.form.components.InputAndLabelWrapper;

class TextFieldAndLabelWrapper extends InputAndLabelWrapper {

    public TextFieldAndLabelWrapper(String labelText, String initValue) {
        super();
        init(labelText, initValue);
    }

    private void initInputField(String initValue) {
        this.inputField = INPUT_COMPONENT_FACTORY.createTextField(initValue);
    }

    private void init(String labelText, String initValue) {
        initLabelField(labelText);
        addChildComponent(this.labelField);

        initInputField(initValue);
        addChildComponent(this.inputField);
    }
}
