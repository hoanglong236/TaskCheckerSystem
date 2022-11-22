package org.swing.app.view.components.form.components.factory.impl;

import com.toedter.calendar.JDateChooser;
import org.swing.app.view.components.form.components.FormInputComponent;

import java.util.Date;

public class FormDateChooser extends FormInputComponent {

    public FormDateChooser(Date initValue) {
        super();
        this.component = new JDateChooser();
        setValue(initValue);
    }

    @Override
    public void setValue(Object value) {
        if (value == null) {
            clear();
            return;
        }
        if (!(value instanceof Date)) {
            throw new IllegalArgumentException();
        }
        ((JDateChooser) this.component).setDate((Date) value);
    }

    @Override
    public Object getValue() {
        return ((JDateChooser) this.component).getDate();
    }

    @Override
    public void clear() {

    }
}
