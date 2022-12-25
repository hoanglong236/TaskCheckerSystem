package org.swing.app.view.components.form.components.wrapper;

import org.swing.app.view.components.form.components.InputComponentWrapper;
import org.swing.app.view.components.form.components.factory.InputComponentFactory;

import java.time.LocalDateTime;

public class DatetimeChooserWrapper extends InputComponentWrapper<LocalDateTime> {

    public DatetimeChooserWrapper(String labelText, LocalDateTime initValue) {
        super();
        init(labelText, initValue);
    }

    private void initInputField(LocalDateTime initValue) {
        this.inputField = InputComponentFactory.createDateTimeChooser(initValue);
    }

    private void init(String labelText, LocalDateTime initValue) {
        initLabelField(labelText);
        addChildComponent(this.labelField);

        initInputField(initValue);
        addChildComponent(this.inputField);
    }
}
