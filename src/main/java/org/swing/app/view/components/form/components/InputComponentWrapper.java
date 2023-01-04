package org.swing.app.view.components.form.components;

import org.swing.app.view.components.ViewComponent;

import java.util.Optional;

public interface InputComponentWrapper<T> extends ViewComponent {

    void setLabelFieldWidth(short labelFieldWidth);

    void setRateOfLabelFieldWidthInTotal(float rateOfLabelFieldWidthInTotal);

    void setValue(T value);

    Optional<T> getValue();

    void clear();
}
