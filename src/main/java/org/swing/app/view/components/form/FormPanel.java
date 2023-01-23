package org.swing.app.view.components.form;

import org.swing.app.view.components.PanelWrapperComponent;
import org.swing.app.view.components.ViewComponent;
import org.swing.app.view.components.form.components.wrapper.InputComponentWrapper;

import java.util.Iterator;

public abstract class FormPanel<T> extends PanelWrapperComponent {

    /**
     * TODO: comment this
     * @return
     */
    public abstract String validate();

    public abstract void setFormData(T e);

    public abstract T getFormData();

    public void clear() {
        final Iterator<ViewComponent> childComponentIterator = getChildComponentIterator();

        while (childComponentIterator.hasNext()) {
            final ViewComponent childComponent = childComponentIterator.next();
            if (childComponent instanceof InputComponentWrapper) {
                ((InputComponentWrapper<?>) childComponent).clear();
            }
        }
    }

    public void setLabelWidthForAllInputWrappers(int width) {
        final Iterator<ViewComponent> childComponentIterator = getChildComponentIterator();

        while (childComponentIterator.hasNext()) {
            final ViewComponent childComponent = childComponentIterator.next();
            if (childComponent instanceof InputComponentWrapper) {
                ((InputComponentWrapper<?>) childComponent).setLabelFieldWidth(width);
            }
        }
    }

    public void setRateOfLabelWidthForAllInputWrappers(float rateOfLabelWidthInWrapper) {
        final Iterator<ViewComponent> childComponentIterator = getChildComponentIterator();

        while (childComponentIterator.hasNext()) {
            final ViewComponent childComponent = childComponentIterator.next();
            if (childComponent instanceof InputComponentWrapper) {
                ((InputComponentWrapper<?>) childComponent).setRateOfLabelFieldWidthInTotal(rateOfLabelWidthInWrapper);
            }
        }
    }
}
