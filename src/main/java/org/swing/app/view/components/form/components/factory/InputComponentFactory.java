package org.swing.app.view.components.form.components.factory;

import org.swing.app.view.components.form.components.input.ComboBox;
import org.swing.app.view.components.form.components.input.DateChooser;
import org.swing.app.view.components.form.components.input.DateTimeChooser;
import org.swing.app.view.components.form.components.InputComponent;
import org.swing.app.view.components.form.components.input.TextArea;
import org.swing.app.view.components.form.components.input.TextField;
import org.swing.app.view.components.form.components.input.YesNoOptionChooser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

public class InputComponentFactory {

    public static InputComponent<String> createTextField(String text) {
        return new TextField(text);
    }

    public static InputComponent<String> createTextField() {
        return createTextField("");
    }

    public static InputComponent<String> createTextArea(String text) {
        return new TextArea(text);
    }

    public static InputComponent<String> createTextArea() {
        return createTextArea("");
    }

    public static InputComponent<String> createComboBox(Set<String> valueRange, String value) {
        return new ComboBox(valueRange, value);
    }

    public static InputComponent<String> createComboBox(Set<String> valueRange) {
        return createComboBox(valueRange, null);
    }

    public static InputComponent<LocalDate> createDateChooser(LocalDate date) {
        return new DateChooser(date);
    }

    public static InputComponent<LocalDate> createDateChooser() {
        return createDateChooser(null);
    }

    public static InputComponent<LocalDateTime> createDateTimeChooser(LocalDateTime dateTime) {
        return new DateTimeChooser(dateTime);
    }

    public static InputComponent<LocalDateTime> createDateTimeChooser() {
        return createDateTimeChooser(null);
    }

    public static InputComponent<Boolean> createYesNoOptionChooser(boolean value) {
        return new YesNoOptionChooser(value);
    }

    public static InputComponent<Boolean> createYesNoOptionChooser() {
        return createYesNoOptionChooser(false);
    }
}
