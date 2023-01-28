package org.swing.app.view.components.form.components.wrapper;

import org.swing.app.view.common.ReserveSizeConstants;
import org.swing.app.view.components.form.components.input.InputComponent;

import java.awt.Dimension;

public class SingleInputComponentWrapper<T> extends InputComponentWrapperBase<T> {

    protected InputComponent<T> inputField;

    public SingleInputComponentWrapper() {
        super();
    }

    @Override
    protected void loadChildComponentsSize() {
        final int availableWidth = getSize().width - ReserveSizeConstants.LARGE_RESERVE_WIDTH;
        final int availableHeight = getSize().height - ReserveSizeConstants.LARGE_RESERVE_HEIGHT;

        final int maxChildComponentHeight = availableHeight - VERTICAL_GAP;

        if (this.rateOfLabelFieldWidthInTotal > 0) {
            this.labelFieldWidth = (int) (this.rateOfLabelFieldWidthInTotal * availableHeight);
        }
        this.childComponentSizeMap.put(this.labelField, new Dimension(this.labelFieldWidth, maxChildComponentHeight));

        final int inputFieldWidth = availableWidth - HORIZONTAL_GAP - this.labelFieldWidth - HORIZONTAL_GAP;
        this.childComponentSizeMap.put(this.inputField, new Dimension(inputFieldWidth, maxChildComponentHeight));
    }

    @Override
    public void setValue(T value) {
        this.inputField.setValue(value);
    }

    @Override
    public T getValue() {
        return this.inputField.getValue();
    }

    @Override
    public void clear() {
        this.inputField.clear();
    }
}
