package org.swing.app.view.components.form.components.factory;

import org.swing.app.view.components.form.components.InputComponentWrapper;
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

    public static InputComponentWrapper<LocalDateTime> createDateTimeChooserWrapper(
            String labelText, LocalDateTime initValue) {

        return new DateTimeChooserWrapper(labelText, initValue);
    }

    public static InputComponentWrapper<LocalDateTime> createDateTimeChooserWrapper(String labelText) {
        return createDateTimeChooserWrapper(labelText, null);
    }

    public static InputComponentWrapper<Boolean> createYesNoOptionChooserWrapper(String labelText, boolean initValue) {
        return new YesNoOptionChooserWrapper(labelText, initValue);
    }

    public static InputComponentWrapper<Boolean> createYesNoOptionChooserWrapper(String labelText) {
        return createYesNoOptionChooserWrapper(labelText, false);
    }
}
