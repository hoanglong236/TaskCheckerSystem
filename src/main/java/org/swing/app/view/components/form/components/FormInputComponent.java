package org.swing.app.view.components.form.components;

import org.swing.app.view.components.SimpleComponent;

public abstract class FormInputComponent extends SimpleComponent {

    public abstract void setValue(Object value);
    public abstract Object getValue();
    public abstract void clear();
}
