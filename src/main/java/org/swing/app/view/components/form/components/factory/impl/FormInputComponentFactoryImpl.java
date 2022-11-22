package org.swing.app.view.components.form.components.factory.impl;

import org.swing.app.view.components.form.components.FormInputComponent;
import org.swing.app.view.components.form.components.factory.FormInputComponentFactory;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

public class FormInputComponentFactoryImpl implements FormInputComponentFactory {

    @Override
    public FormInputComponent createLabelField(String text) {
        return new FormLabelField(text);
    }

    @Override
    public FormInputComponent createLabelArea(String text) {
        return null;
    }

    @Override
    public FormInputComponent createTextField(String text) {
        return new FormTextField(text);
    }

    @Override
    public FormInputComponent createTextArea(String text) {
        return new FormTextArea(text);
    }

    @Override
    public FormInputComponent createComboBox(Set<String> valueRange, String value) {
        return new FormComboBox(valueRange, value);
    }

    @Override
    public FormInputComponent createDateChooser(Date date) {
        return null;
    }

    @Override
    public FormInputComponent createDateTimeChooser(LocalDateTime dateTime) {
        return null;
    }
}
