package org.swing.app.view.components.form.components.wrapper;

import org.swing.app.view.components.ViewComponent;

import java.util.Optional;

public interface InputComponentWrapper<T> extends ViewComponent {

    void setLabelFieldWidth(int labelFieldWidth);

    void setRateOfLabelFieldWidthInTotal(float rateOfLabelFieldWidthInTotal);

    void setValue(T value);

    T getValue();

    void clear();
}
