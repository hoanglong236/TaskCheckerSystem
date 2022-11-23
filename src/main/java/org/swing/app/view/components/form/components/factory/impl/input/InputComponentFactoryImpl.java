package org.swing.app.view.components.form.components.factory.impl.input;

import org.swing.app.view.components.form.components.InputComponent;
import org.swing.app.view.components.form.components.factory.InputComponentFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

public class InputComponentFactoryImpl implements InputComponentFactory {

    @Override
    public InputComponent createLabelField(String text) {
        return new LabelField(text);
    }

    @Override
    public InputComponent createLabelArea(String text) {
        return new LabelArea(text);
    }

    @Override
    public InputComponent createTextField(String text) {
        return new TextField(text);
    }

    @Override
    public InputComponent createTextArea(String text) {
        return new TextArea(text);
    }

    @Override
    public InputComponent createComboBox(Set<String> valueRange, String value) {
        return new ComboBox(valueRange, value);
    }

    @Override
    public InputComponent createDateChooser(LocalDate date) {
        return new DateChooser(date);
    }

    @Override
    public InputComponent createDateTimeChooser(LocalDateTime dateTime) {
        return new DateTimeChooser(dateTime);
    }
}
