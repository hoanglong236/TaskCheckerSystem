package org.swing.app.view.components.form;

import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.PanelWrapperComponent;

import java.awt.FlowLayout;

public abstract class FormBase<T> extends PanelWrapperComponent implements Form<T> {

    protected static final FlowLayout MAIN_LAYOUT = new FlowLayout(FlowLayout.LEFT,
            ViewConstant.LARGE_H_GAP, ViewConstant.LARGE_V_GAP);

    public FormBase() {
        setLayout(MAIN_LAYOUT);
    }

    public abstract T getFormData();

    public abstract void setFormData(T formData);

    public abstract void reset();

    public abstract void clear();
}