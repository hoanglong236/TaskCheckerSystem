package org.swing.app.view.components.form.components.factory.impl.wrapper;

import org.swing.app.view.components.form.components.InputAndLabelWrapper;

import java.time.LocalDateTime;

public class DateTimeChooserAndLabelWrapper extends InputAndLabelWrapper {

    public DateTimeChooserAndLabelWrapper(String labelText, LocalDateTime initValue) {
        super();
        init(labelText, initValue);
    }

    private void initInputField(LocalDateTime initValue) {
        this.inputField = INPUT_COMPONENT_FACTORY.createDateTimeChooser(initValue);
    }

    private void init(String labelText, LocalDateTime initValue) {
        initLabelField(labelText);
        addChildComponent(this.labelField);

        initInputField(initValue);
        addChildComponent(this.inputField);
    }
}
