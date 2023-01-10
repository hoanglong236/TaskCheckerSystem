package org.swing.app.view.components.form.components;

import org.swing.app.view.components.ViewComponent;

public interface InputComponent<T> extends ViewComponent {

    void setValue(T value);
    T getValue();
    void clear();
}
