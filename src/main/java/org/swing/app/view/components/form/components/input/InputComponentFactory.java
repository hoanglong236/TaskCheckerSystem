package org.swing.app.view.components.form.components.input;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

public class InputComponentFactory {

    public static InputComponent createLabelField(String text) {
        return new LabelField(text);
    }

    public static InputComponent createLabelField() {
        return new LabelField(null);
    }

    public static InputComponent createLabelArea(String text) {
        return new LabelArea(text);
    }

    public static InputComponent createLabelArea() {
        return new LabelArea(null);
    }

    public static InputComponent createTextField(String text) {
        return new TextField(text);
    }

    public static InputComponent createTextField() {
        return new TextField(null);
    }

    public static InputComponent createTextArea(String text) {
        return new TextArea(text);
    }

    public static InputComponent createTextArea() {
        return new TextArea(null);
    }

    public static InputComponent createComboBox(Set<String> valueRange, String value) {
        return new ComboBox(valueRange, value);
    }

    public static InputComponent createComboBox(Set<String> valueRange) {
        return new ComboBox(valueRange, null);
    }

    public static InputComponent createDateChooser(LocalDate date) {
        return new DateChooser(date);
    }

    public static InputComponent createDateChooser() {
        return new DateChooser(null);
    }

    public static InputComponent createDateTimeChooser(LocalDateTime dateTime) {
        return new DateTimeChooser(dateTime);
    }

    public static InputComponent createDateTimeChooser() {
        return new DateTimeChooser(null);
    }
}
