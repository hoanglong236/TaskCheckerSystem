package org.swing.app.view.components.form.components.factory.impl.wrapper;

import org.swing.app.view.components.form.components.InputAndLabelWrapper;
import org.swing.app.view.components.form.components.factory.InputAndLabelWrapperFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

public class InputAndLabelWrapperFactoryImpl implements InputAndLabelWrapperFactory {

    @Override
    public InputAndLabelWrapper createTextAndLabelWrapper(String labelText, String initValue) {
        return new TextFieldAndLabelWrapper(labelText, initValue);
    }

    @Override
    public InputAndLabelWrapper createTextAreaAndLabelWrapper(String labelText, String initValue) {
        return new TextAreaAndLabelWrapper(labelText, initValue);
    }

    @Override
    public InputAndLabelWrapper createComboBoxAndLabelWrapper(String labelText, Set<String> valueRange,
            String initValue) {
        return new ComboBoxAndLabelWrapper(labelText, valueRange, initValue);
    }

    @Override
    public InputAndLabelWrapper createDateChooserAndLabelWrapper(String labelText, LocalDate initValue) {
        return new DateChooserAndLabelWrapper(labelText, initValue);
    }

    @Override
    public InputAndLabelWrapper createDateTimeChooserAndLabelWrapper(String labelText, LocalDateTime initValue) {
        return new DateTimeChooserAndLabelWrapper(labelText, initValue);
    }
}
