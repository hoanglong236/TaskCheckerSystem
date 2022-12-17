package org.swing.app.view.components.form.components.factory;

import org.swing.app.view.components.form.components.wrapper.LabelAndComboBoxWrapper;
import org.swing.app.view.components.form.components.wrapper.LabelAndDateChooserWrapper;
import org.swing.app.view.components.form.components.wrapper.LabelAndDateTimeChooserWrapper;
import org.swing.app.view.components.form.components.LabelAndInputWrapper;
import org.swing.app.view.components.form.components.wrapper.LabelAndTextAreaWrapper;
import org.swing.app.view.components.form.components.wrapper.LabelAndTextFieldWrapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

public class LabelAndInputWrapperFactory {

    public static LabelAndInputWrapper createLabelAndTextFieldWrapper(String labelText, String initValue) {
        return new LabelAndTextFieldWrapper(labelText, initValue);
    }

    public static LabelAndInputWrapper createLabelAndTextFieldWrapper(String labelText) {
        return createLabelAndTextFieldWrapper(labelText, null);
    }

    public static LabelAndInputWrapper createLabelAndTextAreaWrapper(String labelText, String initValue) {
        return new LabelAndTextAreaWrapper(labelText, initValue);
    }

    public static LabelAndInputWrapper createLabelAndTextAreaWrapper(String labelText) {
        return createLabelAndTextAreaWrapper(labelText, null);
    }

    public static LabelAndInputWrapper createLabelAndComboBoxWrapper(
            String labelText, Set<String> valueRange, String initValue) {

        return new LabelAndComboBoxWrapper(labelText, valueRange, initValue);
    }

    public static LabelAndInputWrapper createLabelAndComboBoxWrapper(String labelText, Set<String> valueRange) {
        return createLabelAndComboBoxWrapper(labelText, valueRange, null);
    }

    public static LabelAndInputWrapper createLabelAndDateChooserWrapper(String labelText, LocalDate initValue) {
        return new LabelAndDateChooserWrapper(labelText, initValue);
    }

    public static LabelAndInputWrapper createLabelAndDateChooserWrapper(String labelText) {
        return createLabelAndDateChooserWrapper(labelText, null);
    }

    public static LabelAndInputWrapper createLabelAndDateTimeChooserWrapper(
            String labelText, LocalDateTime initValue) {

        return new LabelAndDateTimeChooserWrapper(labelText, initValue);
    }

    public static LabelAndInputWrapper createLabelAndDateTimeChooserWrapper(String labelText) {
        return createLabelAndDateTimeChooserWrapper(labelText, null);
    }
}
