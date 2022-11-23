package org.swing.app.view.components.form.components.factory.impl.wrapper;

import org.swing.app.view.components.form.components.InputAndLabelWrapper;

class TextAreaAndLabelWrapper extends InputAndLabelWrapper {

    public TextAreaAndLabelWrapper(String labelText, String initValue) {
        super();
        init(labelText, initValue);
    }

    @Override
    protected void initLabelField(String labelText) {
        this.labelField = INPUT_COMPONENT_FACTORY.createLabelArea(labelText);
    }

    private void initInputField(String initValue) {
        this.inputField = INPUT_COMPONENT_FACTORY.createTextArea(initValue);
    }

    private void init(String labelText, String initValue) {
        initLabelField(labelText);
        addChildComponent(this.labelField);

        initInputField(initValue);
        addChildComponent(this.inputField);
    }
}
