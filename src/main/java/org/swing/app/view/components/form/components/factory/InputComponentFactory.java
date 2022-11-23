package org.swing.app.view.components.form.components.factory;

import org.swing.app.view.components.form.components.InputComponent;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

public interface InputComponentFactory {

    InputComponent createLabelField(String text);
    InputComponent createLabelArea(String text);
    InputComponent createTextField(String text);
    InputComponent createTextArea(String text);
    InputComponent createComboBox(Set<String> valueRange, String value);
    InputComponent createDateChooser(LocalDate date);
    InputComponent createDateTimeChooser(LocalDateTime dateTime);
}
