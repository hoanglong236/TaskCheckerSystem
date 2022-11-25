package org.swing.app.view.components.form.components.factory.wrapper;

import org.swing.app.view.components.form.components.InputAndLabelWrapper;
import org.swing.app.view.components.form.components.factory.input.InputComponentFactory;

import java.time.LocalDate;

public class DateChooserAndLabelWrapper extends InputAndLabelWrapper {

    public DateChooserAndLabelWrapper(String labelText, LocalDate initValue) {
        super();
        init(labelText, initValue);
    }

    private void initInputField(LocalDate initValue) {
        this.inputField = InputComponentFactory.createDateChooser(initValue);
    }

    private void init(String labelText, LocalDate initValue) {
        initLabelField(labelText);
        addChildComponent(this.labelField);

        initInputField(initValue);
        addChildComponent(this.inputField);
    }
}
