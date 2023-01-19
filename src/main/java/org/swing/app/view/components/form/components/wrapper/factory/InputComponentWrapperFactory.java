package org.swing.app.view.components.form.components.wrapper.factory;

import org.swing.app.view.components.form.components.wrapper.InputComponentWrapper;
import org.swing.app.view.components.form.components.wrapper.ComboBoxWrapper;
import org.swing.app.view.components.form.components.wrapper.DateChooserWrapper;
import org.swing.app.view.components.form.components.wrapper.DateTimeChooserWrapper;
import org.swing.app.view.components.form.components.wrapper.TextAreaWrapper;
import org.swing.app.view.components.form.components.wrapper.TextFieldWrapper;
import org.swing.app.view.components.form.components.wrapper.YesNoOptionChooserWrapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

public class InputComponentWrapperFactory {

    public static InputComponentWrapper<String> createTextFieldWrapper(String labelText) {
        return new TextFieldWrapper(labelText);
    }

    public static InputComponentWrapper<String> createTextAreaWrapper(String labelText) {
        return new TextAreaWrapper(labelText);
    }

    public static InputComponentWrapper<String> createComboBoxWrapper(String labelText, Set<String> valueRange) {
        return new ComboBoxWrapper(labelText, valueRange);
    }

    public static InputComponentWrapper<LocalDate> createDateChooserWrapper(String labelText) {
        return new DateChooserWrapper(labelText);
    }

    public static InputComponentWrapper<LocalDateTime> createDateTimeChooserWrapper(String labelText) {
        return new DateTimeChooserWrapper(labelText);
    }

    public static InputComponentWrapper<Boolean> createYesNoOptionChooserWrapper(String labelText) {
        return new YesNoOptionChooserWrapper(labelText);
    }
}
