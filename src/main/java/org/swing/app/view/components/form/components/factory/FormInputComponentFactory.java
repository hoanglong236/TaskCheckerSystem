package org.swing.app.view.components.form.components.factory;

import org.swing.app.view.components.form.components.FormInputComponent;

import java.util.Set;

public interface FormInputComponentFactory {

    FormInputComponent createLabelField(String text);
    FormInputComponent createTextField(String text);
    FormInputComponent createTextArea(String text);
    FormInputComponent createComboBox(Set<String> valueRanges, String value);
    FormInputComponent createRadioField();
    FormInputComponent createDateTimeChooser();
}
