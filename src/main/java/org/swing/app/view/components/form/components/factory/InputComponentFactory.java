package org.swing.app.view.components.form.components.factory;

import org.swing.app.view.components.form.components.input.ComboBox;
import org.swing.app.view.components.form.components.input.DateChooser;
import org.swing.app.view.components.form.components.input.DateTimeChooser;
import org.swing.app.view.components.form.components.InputComponent;
import org.swing.app.view.components.form.components.input.LabelArea;
import org.swing.app.view.components.form.components.input.LabelField;
import org.swing.app.view.components.form.components.input.TextArea;
import org.swing.app.view.components.form.components.input.TextField;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

public class InputComponentFactory {

    public static InputComponent createLabelField(String text) {
        return new LabelField(text);
    }

    public static InputComponent createLabelField() {
        return createLabelField(null);
    }

    public static InputComponent createLabelArea(String text) {
        return new LabelArea(text);
    }

    public static InputComponent createLabelArea() {
        return createLabelArea(null);
    }

    public static InputComponent createTextField(String text) {
        return new TextField(text);
    }

    public static InputComponent createTextField() {
        return createTextField(null);
    }

    public static InputComponent createTextArea(String text) {
        return new TextArea(text);
    }

    public static InputComponent createTextArea() {
        return createTextArea(null);
    }

    public static InputComponent createComboBox(Set<String> valueRange, String value) {
        if (valueRange == null || valueRange.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return new ComboBox(valueRange, value);
    }

    public static InputComponent createComboBox(Set<String> valueRange) {
        return createComboBox(valueRange, null);
    }

    public static InputComponent createDateChooser(LocalDate date) {
        return new DateChooser(date);
    }

    public static InputComponent createDateChooser() {
        return createDateChooser(null);
    }

    public static InputComponent createDateTimeChooser(LocalDateTime dateTime) {
        return new DateTimeChooser(dateTime);
    }

    public static InputComponent createDateTimeChooser() {
        return createDateTimeChooser(null);
    }
}
