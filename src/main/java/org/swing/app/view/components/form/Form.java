package org.swing.app.view.components.form;

import org.swing.app.view.components.ViewComponent;

public interface Form<T> extends ViewComponent {

    String validate();
    T getFormData();
    void setFormData(T e);
    void clear();
}
