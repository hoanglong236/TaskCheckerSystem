package org.swing.app.view.components.form.components.factory;

import org.swing.app.view.components.form.components.InputAndLabelWrapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

public interface InputAndLabelWrapperFactory {

    InputAndLabelWrapper createTextAndLabelWrapper(String labelText, String initValue);
    InputAndLabelWrapper createTextAreaAndLabelWrapper(String labelText, String initValue);
    InputAndLabelWrapper createComboBoxAndLabelWrapper(String labelText, Set<String> valueRange, String initValue);
    InputAndLabelWrapper createDateChooserAndLabelWrapper(String labelText, LocalDate date);
    InputAndLabelWrapper createDateTimeChooserAndLabelWrapper(String labelText, LocalDateTime dateTime);
}
