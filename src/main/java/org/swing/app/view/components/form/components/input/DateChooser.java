package org.swing.app.view.components.form.components.input;

import com.toedter.calendar.JDateChooser;
import org.swing.app.util.DateConverter;
import org.swing.app.view.components.SimpleComponent;
import org.swing.app.view.components.factory.JComponentFactory;
import org.swing.app.view.components.form.components.InputComponent;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

public class DateChooser extends SimpleComponent implements InputComponent<LocalDate> {

    public DateChooser(LocalDate initValue) {
        super();
        this.component = JComponentFactory.createJDateChooser();
        setValue(initValue);
    }

    @Override
    public void setValue(LocalDate value) {
        if (value == null) {
            clear();
            return;
        }
        ((JDateChooser) this.component).setDate(DateConverter.toDate(value));
    }

    @Override
    public Optional<LocalDate> getValue() {
        final Date chosenDate = ((JDateChooser) this.component).getDate();
        if (chosenDate == null) {
            return Optional.empty();
        }
        return Optional.of(DateConverter.toLocalDate(chosenDate));
    }


    @Override
    public void clear() {
        ((JDateChooser) this.component).cleanup();
    }
}
