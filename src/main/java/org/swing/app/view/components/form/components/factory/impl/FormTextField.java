package org.swing.app.view.components.form.components.factory.impl;

import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.SimpleComponent;
import org.swing.app.view.components.form.components.FormInputComponent;

import javax.swing.JTextField;
import java.awt.Font;

class FormTextField extends SimpleComponent implements FormInputComponent {

    private static final Font TEXT_FIELD_FONT = new Font(ViewConstant.PRIMARY_FONT_NAME,
            Font.PLAIN, ViewConstant.F_TEXT_FIELD_FONT_SIZE);

    public FormTextField(Object initValue) {
        super();
        this.component = new JTextField();
        this.component.setFont(TEXT_FIELD_FONT);
        setValue(initValue);
    }

    @Override
    public void setValue(Object value) {
        if (value == null) {
            clear();
            return;
        }
        try {
            ((JTextField) this.component).setText((String) value);
        } catch (ClassCastException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Object getValue() {
        String inputValue = ((JTextField) this.component).getText();
        return inputValue == null ? null : inputValue.trim();
    }

    @Override
    public void clear() {
        ((JTextField) this.component).setText("");
    }
}
