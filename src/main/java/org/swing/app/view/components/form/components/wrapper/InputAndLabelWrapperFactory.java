package org.swing.app.view.components.form.components.wrapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

public class InputAndLabelWrapperFactory {

    public static InputAndLabelWrapper createTextAndLabelWrapper(String labelText, String initValue) {
        return new TextFieldAndLabelWrapper(labelText, initValue);
    }

    public static InputAndLabelWrapper createTextAndLabelWrapper(String labelText) {
        return new TextFieldAndLabelWrapper(labelText, null);
    }

    public static InputAndLabelWrapper createTextAreaAndLabelWrapper(String labelText, String initValue) {
        return new TextAreaAndLabelWrapper(labelText, initValue);
    }

    public static InputAndLabelWrapper createTextAreaAndLabelWrapper(String labelText) {
        return new TextAreaAndLabelWrapper(labelText, null);
    }

    public static InputAndLabelWrapper createComboBoxAndLabelWrapper(String labelText, Set<String> valueRange,
            String initValue) {
        return new ComboBoxAndLabelWrapper(labelText, valueRange, initValue);
    }

    public static InputAndLabelWrapper createComboBoxAndLabelWrapper(String labelText, Set<String> valueRange) {
        return new ComboBoxAndLabelWrapper(labelText, valueRange, null);
    }

    public static InputAndLabelWrapper createDateChooserAndLabelWrapper(String labelText, LocalDate initValue) {
        return new DateChooserAndLabelWrapper(labelText, initValue);
    }

    public static InputAndLabelWrapper createDateChooserAndLabelWrapper(String labelText) {
        return new DateChooserAndLabelWrapper(labelText, null);
    }

    public static InputAndLabelWrapper createDateTimeChooserAndLabelWrapper(String labelText, LocalDateTime initValue) {
        return new DateTimeChooserAndLabelWrapper(labelText, initValue);
    }

    public static InputAndLabelWrapper createDateTimeChooserAndLabelWrapper(String labelText) {
        return new DateTimeChooserAndLabelWrapper(labelText, null);
    }
}
