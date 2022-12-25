package org.swing.app.view.components.form.components.factory;

import org.swing.app.view.components.form.components.InputComponentWrapper;
import org.swing.app.view.components.form.components.wrapper.ComboBoxWrapper;
import org.swing.app.view.components.form.components.wrapper.DateChooserWrapper;
import org.swing.app.view.components.form.components.wrapper.DatetimeChooserWrapper;
import org.swing.app.view.components.form.components.wrapper.TextAreaWrapper;
import org.swing.app.view.components.form.components.wrapper.TextFieldWrapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

public class InputComponentWrapperFactory {

    public static InputComponentWrapper<String> createTextFieldWrapper(String labelText, String initValue) {
        return new TextFieldWrapper(labelText, initValue);
    }

    public static InputComponentWrapper<String> createTextFieldWrapper(String labelText) {
        return createTextFieldWrapper(labelText, null);
    }

    public static InputComponentWrapper<String> createTextAreaWrapper(String labelText, String initValue) {
        return new TextAreaWrapper(labelText, initValue);
    }

    public static InputComponentWrapper<String> createTextAreaWrapper(String labelText) {
        return createTextAreaWrapper(labelText, null);
    }

    public static InputComponentWrapper<String> createComboBoxWrapper(
            String labelText, Set<String> valueRange, String initValue) {

        return new ComboBoxWrapper(labelText, valueRange, initValue);
    }

    public static InputComponentWrapper<String> createComboBoxWrapper(String labelText, Set<String> valueRange) {
        return createComboBoxWrapper(labelText, valueRange, null);
    }

    public static InputComponentWrapper<LocalDate> createDateChooserWrapper(String labelText, LocalDate initValue) {
        return new DateChooserWrapper(labelText, initValue);
    }

    public static InputComponentWrapper<LocalDate> createDateChooserWrapper(String labelText) {
        return createDateChooserWrapper(labelText, null);
    }

    public static InputComponentWrapper<LocalDateTime> createDatetimeChooserWrapper(
            String labelText, LocalDateTime initValue) {

        return new DatetimeChooserWrapper(labelText, initValue);
    }

    public static InputComponentWrapper<LocalDateTime> createDatetimeChooserWrapper(String labelText) {
        return createDatetimeChooserWrapper(labelText, null);
    }
}
