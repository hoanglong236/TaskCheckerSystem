package org.swing.app.view.components.form.components.input;

import org.swing.app.view.components.ViewComponent;

public interface InputComponent extends ViewComponent {

    void setValue(Object value);
    Object getValue();
    void clear();
}