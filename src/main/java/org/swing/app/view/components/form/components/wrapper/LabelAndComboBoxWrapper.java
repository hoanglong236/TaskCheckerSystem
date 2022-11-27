package org.swing.app.view.components.form.components.wrapper;

import org.swing.app.view.components.form.components.input.InputComponentFactory;

import java.util.Set;

class LabelAndComboBoxWrapper extends LabelAndInputWrapper {

    public LabelAndComboBoxWrapper(String labelText, Set<String> valueRange, String initValue) {
        super();
        init(labelText, valueRange, initValue);
    }

    private void initInputField(Set<String> valueRange, String initValue) {
        this.inputField = InputComponentFactory.createComboBox(valueRange, initValue);
    }

    private void init(String labelText, Set<String> valueRange, String initValue) {
        initLabelField(labelText);
        addChildComponent(this.labelField);

        initInputField(valueRange, initValue);
        addChildComponent(this.inputField);
    }
}
