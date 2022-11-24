package org.swing.app.view.components.form;

import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.WrapperComponent;
import org.swing.app.view.components.form.components.factory.InputAndLabelWrapperFactory;
import org.swing.app.view.components.form.components.factory.impl.wrapper.InputAndLabelWrapperFactoryImpl;

import java.awt.FlowLayout;

public abstract class FormBase<T> extends WrapperComponent implements Form<T> {

    protected static final FlowLayout MAIN_LAYOUT = new FlowLayout(FlowLayout.LEFT,
            ViewConstant.LARGE_H_GAP, ViewConstant.LARGE_V_GAP);
    protected static final InputAndLabelWrapperFactory INPUT_AND_LABEL_WRAPPER_FACTORY
            = new InputAndLabelWrapperFactoryImpl();
}
