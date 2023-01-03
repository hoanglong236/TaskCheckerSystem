package org.swing.app.view.components.form.components.factory;

import org.swing.app.view.components.form.components.input.ComboBox;
import org.swing.app.view.components.form.components.input.DateChooser;
import org.swing.app.view.components.form.components.InputComponent;
import org.swing.app.view.components.form.components.input.TextArea;
import org.swing.app.view.components.form.components.input.TextField;
import org.swing.app.view.components.form.components.input.YesNoOptionChooser;

import java.time.LocalDate;
import java.util.Set;

public class InputComponentFactory {

    public static InputComponent<String> createTextField() {
        return new TextField();
    }

    public static InputComponent<String> createTextArea() {
        return new TextArea();
    }

    public static InputComponent<String> createComboBox(Set<String> valueRange) {
        return new ComboBox(valueRange);
    }

    public static InputComponent<LocalDate> createDateChooser() {
        return new DateChooser();
    }

    public static InputComponent<Boolean> createYesNoOptionChooser() {
        return new YesNoOptionChooser();
    }
}
