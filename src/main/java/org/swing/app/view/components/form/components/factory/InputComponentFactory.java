package org.swing.app.view.components.form.components.factory;

import org.swing.app.view.components.form.components.InputComponent;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

public interface InputComponentFactory {

    InputComponent createLabelField(String text);
    InputComponent createLabelField();

    InputComponent createLabelArea(String text);
    InputComponent createLabelArea();

    InputComponent createTextField(String text);
    InputComponent createTextField();

    InputComponent createTextArea(String text);
    InputComponent createTextArea();

    InputComponent createComboBox(Set<String> valueRange, String value);
    InputComponent createComboBox(Set<String> valueRange);

    InputComponent createDateChooser(LocalDate date);
    InputComponent createDateChooser();

    InputComponent createDateTimeChooser(LocalDateTime dateTime);
    InputComponent createDateTimeChooser();
}
