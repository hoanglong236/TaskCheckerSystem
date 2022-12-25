package org.swing.app.view.components.form.components;

import org.swing.app.view.components.ViewComponent;

import java.util.Optional;

public interface InputComponent<T> extends ViewComponent {

    void setValue(T value);
    Optional<T> getValue();
    void clear();
}
