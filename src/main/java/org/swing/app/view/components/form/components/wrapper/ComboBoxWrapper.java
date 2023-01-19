package org.swing.app.view.components.form.components.wrapper;

import org.swing.app.view.components.form.components.input.factory.InputComponentFactory;

import java.util.Set;

public class ComboBoxWrapper extends SingleInputComponentWrapper<String> {

    public ComboBoxWrapper(String labelText, Set<String> valueRange) {
        super();
        init(labelText, valueRange);
    }

    private void initInputField(Set<String> valueRange) {
        this.inputField = InputComponentFactory.createComboBox(valueRange);
        this.inputField.setFont(DEFAULT_INPUT_FIELD_FONT);
    }

    private void init(String labelText, Set<String> valueRange) {
        initLabelField(labelText);
        addChildComponent(this.labelField);

        initInputField(valueRange);
        addChildComponent(this.inputField);
    }
}
