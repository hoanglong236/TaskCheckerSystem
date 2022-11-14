package org.swing.app.view.components.form.components.factory.impl;

import org.swing.app.view.components.form.components.FormInputComponent;
import org.swing.app.view.components.form.components.factory.FormInputComponentFactory;

import java.util.Set;

public class FormInputComponentFactoryImpl implements FormInputComponentFactory {

    @Override
    public FormInputComponent createLabelField(String text) {
        return new FormLabelField(text);
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
    public FormInputComponent createComboBox(Set<String> valueRanges, String value) {
        return new FormComboBox<String>(valueRanges, value);
    }

    @Override
    public FormInputComponent createRadioField() {
        return null;
    }

    @Override
    public FormInputComponent createDateTimeChooser() {
        return null;
    }
}
