package org.swing.app.view.components.form.components.wrapper;

import org.swing.app.view.components.factory.UIComponentFactory;
import org.swing.app.view.components.form.components.factory.InputComponentFactory;

public class TextAreaWrapper extends SingleInputComponentWrapper<String> {

    public TextAreaWrapper(String labelText, String initValue) {
        super();
        init(labelText);
        setValue(initValue);
    }

    @Override
    protected void initLabelField(String labelText) {
        this.labelField = UIComponentFactory.createLabelArea(labelText);
    }

    private void initInputField() {
        this.inputField = InputComponentFactory.createTextArea();
    }

    private void init(String labelText) {
        initLabelField(labelText);
        addChildComponent(this.labelField);

        initInputField();
        addChildComponent(this.inputField);
    }
}
