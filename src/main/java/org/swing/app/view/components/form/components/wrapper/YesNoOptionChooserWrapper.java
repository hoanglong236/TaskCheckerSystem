package org.swing.app.view.components.form.components.wrapper;

import org.swing.app.view.components.form.components.input.factory.InputComponentFactory;

public class YesNoOptionChooserWrapper extends SingleInputComponentWrapper<Boolean> {

    public YesNoOptionChooserWrapper(String labelText) {
        super();
        init(labelText);
    }

    private void initInputField() {
        this.inputField = InputComponentFactory.createYesNoOptionChooser();
        this.inputField.setFont(DEFAULT_INPUT_FIELD_FONT);
    }

    private void init(String labelText) {
        initLabelField(labelText);
        addChildComponent(this.labelField);

        initInputField();
        addChildComponent(this.inputField);
    }
}
