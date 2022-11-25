package org.swing.app.view.components.form.components.factory.input;

import com.toedter.calendar.JDateChooser;
import org.swing.app.util.DateConverter;
import org.swing.app.view.components.SimpleComponent;
import org.swing.app.view.components.form.components.InputComponent;

import java.time.LocalDate;
import java.util.Date;

class DateChooser extends SimpleComponent implements InputComponent {

    public DateChooser(LocalDate initValue) {
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
        if (!(value instanceof LocalDate)) {
            throw new IllegalArgumentException();
        }
        ((JDateChooser) this.component).setDate(DateConverter.toDate((LocalDate) value));
    }

    @Override
    public Object getValue() {
        final Date chosenDate = ((JDateChooser) this.component).getDate();
        return DateConverter.toLocalDate(chosenDate);
    }

    @Override
    public void clear() {
        ((JDateChooser) this.component).cleanup();
    }
}
