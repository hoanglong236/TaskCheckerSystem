package org.swing.app.view.components.form.components.wrapper;

import org.swing.app.view.components.factory.UIComponentFactory;
import org.swing.app.view.components.form.components.input.factory.InputComponentFactory;

public class TextAreaWrapper extends SingleInputComponentWrapper<String> {

    public TextAreaWrapper(String labelText) {
        super();
        init(labelText);
    }

    @Override
    protected void initLabelField(String labelText) {
        this.labelField = UIComponentFactory.createLabelArea(labelText);
        this.labelField.setFont(DEFAULT_LABEL_FIELD_FONT);
    }

    private void initInputField() {
        this.inputField = InputComponentFactory.createTextArea();
        this.inputField.setFont(DEFAULT_INPUT_FIELD_FONT);
    }

    private void init(String labelText) {
        initLabelField(labelText);
        addChildComponent(this.labelField);

        initInputField();
        addChildComponent(this.inputField);
    }
}
