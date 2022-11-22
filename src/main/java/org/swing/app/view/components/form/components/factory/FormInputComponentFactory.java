package org.swing.app.view.components.form.components.factory;

import org.swing.app.view.components.form.components.FormInputComponent;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

public interface FormInputComponentFactory {

    FormInputComponent createLabelField(String text);
    FormInputComponent createLabelArea(String text);
    FormInputComponent createTextField(String text);
    FormInputComponent createTextArea(String text);
    FormInputComponent createComboBox(Set<String> valueRange, String value);
    FormInputComponent createDateChooser(Date date);
    FormInputComponent createDateTimeChooser(LocalDateTime dateTime);
}
