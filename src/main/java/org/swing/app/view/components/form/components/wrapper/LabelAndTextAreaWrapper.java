package org.swing.app.view.components.form.components.wrapper;

import org.swing.app.view.components.form.components.factory.InputComponentFactory;
import org.swing.app.view.components.form.components.LabelAndInputWrapper;

public class LabelAndTextAreaWrapper extends LabelAndInputWrapper {

    public LabelAndTextAreaWrapper(String labelText, String initValue) {
        super();
        init(labelText, initValue);
    }

    @Override
    protected void initLabelField(String labelText) {
        this.labelField = InputComponentFactory.createLabelArea(labelText);
    }

    private void initInputField(String initValue) {
        this.inputField = InputComponentFactory.createTextArea(initValue);
    }

    private void init(String labelText, String initValue) {
        initLabelField(labelText);
        addChildComponent(this.labelField);

        initInputField(initValue);
        addChildComponent(this.inputField);
    }
}
