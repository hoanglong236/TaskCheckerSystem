package org.swing.app.view.components.form.components.factory.wrapper;

import org.swing.app.view.components.form.components.InputAndLabelWrapper;
import org.swing.app.view.components.form.components.factory.input.InputComponentFactory;

import java.util.Set;

class ComboBoxAndLabelWrapper extends InputAndLabelWrapper {

    public ComboBoxAndLabelWrapper(String labelText, Set<String> valueRange, String initValue) {
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
