package org.swing.app.view.components.form.components.wrapper;

import org.swing.app.view.components.form.components.factory.InputComponentFactory;

import java.time.LocalDate;

public class DateChooserWrapper extends SingleInputComponentWrapper<LocalDate> {

    public DateChooserWrapper(String labelText) {
        super();
        init(labelText);
    }

    private void initInputField() {
        this.inputField = InputComponentFactory.createDateChooser();
        this.inputField.setFont(DEFAULT_INPUT_FIELD_FONT);
    }

    private void init(String labelText) {
        initLabelField(labelText);
        addChildComponent(this.labelField);

        initInputField();
        addChildComponent(this.inputField);
    }
}
