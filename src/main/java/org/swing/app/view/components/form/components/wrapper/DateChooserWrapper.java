package org.swing.app.view.components.form.components.wrapper;

import org.swing.app.view.components.form.components.InputComponentWrapper;
import org.swing.app.view.components.form.components.factory.InputComponentFactory;

import java.time.LocalDate;

public class DateChooserWrapper extends InputComponentWrapper<LocalDate> {

    public DateChooserWrapper(String labelText, LocalDate initValue) {
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
