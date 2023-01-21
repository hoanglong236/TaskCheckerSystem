package org.swing.app.view.components.form.components.input;

import com.toedter.calendar.JDateChooser;
import org.swing.app.util.DateConverter;
import org.swing.app.view.common.ViewConstants;
import org.swing.app.view.components.SimpleComponent;
import org.swing.app.view.components.factory.JComponentFactory;

import java.time.LocalDate;
import java.util.Date;

public class DateChooser extends SimpleComponent implements InputComponent<LocalDate> {

    private static final String DATE_CHOOSER_FORMAT = ViewConstants.DEFAULT_DATE_CHOOSER_FORMAT;

    public DateChooser() {
        super();
        this.sourceComponent = JComponentFactory.createJDateChooser();
        ((JDateChooser) this.sourceComponent).setDateFormatString(DATE_CHOOSER_FORMAT);
        setDefaultValue();
    }

    private void setDefaultValue() {
        ((JDateChooser) this.sourceComponent).setDate(null);
    }

    @Override
    public void setValue(LocalDate value) {
        if (value == null) {
            clear();
            return;
        }
        ((JDateChooser) this.sourceComponent).setDate(DateConverter.toDate(value));
    }

    @Override
    public LocalDate getValue() {
        final Date chosenDate = ((JDateChooser) this.sourceComponent).getDate();
        if (chosenDate == null) {
            return null;
        }
        return DateConverter.toLocalDate(chosenDate);
    }

    @Override
    public void clear() {
        setDefaultValue();
    }
}
