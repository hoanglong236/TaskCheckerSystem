package org.swing.app.view.components.form.components.factory.impl;

import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.SimpleComponent;
import org.swing.app.view.components.form.components.FormInputComponent;

import javax.swing.JTextArea;
import java.awt.Font;

class FormTextArea extends FormInputComponent {

    private static final Font TEXT_FIELD_FONT = new Font(ViewConstant.PRIMARY_FONT_NAME,
            Font.PLAIN, ViewConstant.F_TEXT_FIELD_FONT_SIZE);

    public FormTextArea(String initValue) {
        super();
        this.component = new JTextArea();
        this.component.setFont(TEXT_FIELD_FONT);
        setValue(initValue);
    }

    @Override
    public void setValue(Object value) {
        if (value == null) {
            clear();
            return;
        }
        if (!(value instanceof String)) {
            throw new IllegalArgumentException();
        }
        ((JTextArea) this.component).setText((String) value);
    }

    @Override
    public Object getValue() {
        final String inputValue = ((JTextArea) this.component).getText();
        return inputValue == null ? null : inputValue.trim();
    }

    @Override
    public void clear() {
        ((JTextArea) this.component).setText("");
    }
}
