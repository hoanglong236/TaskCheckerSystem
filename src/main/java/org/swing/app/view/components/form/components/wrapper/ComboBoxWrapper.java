package org.swing.app.view.components.form.components.wrapper;

import org.swing.app.view.components.form.components.InputComponentWrapper;
import org.swing.app.view.components.form.components.factory.InputComponentFactory;

import java.util.Set;

public class ComboBoxWrapper extends InputComponentWrapper<String> {

    public ComboBoxWrapper(String labelText, Set<String> valueRange, String initValue) {
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
