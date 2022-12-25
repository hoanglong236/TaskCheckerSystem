package org.swing.app.view.components.form.components;

import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.PanelWrapperComponent;
import org.swing.app.view.components.SimpleComponent;
import org.swing.app.view.components.factory.UIComponentFactory;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Optional;

public abstract class InputComponentWrapper<T> extends PanelWrapperComponent {

    private static final byte HORIZONTAL_GAP = ViewConstant.MEDIUM_H_GAP;
    private static final byte VERTICAL_GAP = ViewConstant.MEDIUM_V_GAP;
    private static final FlowLayout MAIN_LAYOUT = new FlowLayout(FlowLayout.LEFT, HORIZONTAL_GAP, VERTICAL_GAP);

    protected SimpleComponent labelField;
    protected InputComponent<T> inputField;

    private int labelFieldWidth;
    private float rateOfLabelFieldWidthInTotal;

    public InputComponentWrapper() {
        super();
        setLayout(MAIN_LAYOUT);
        this.labelFieldWidth = ViewConstant.DEFAULT_LABEL_WIDTH;
        this.rateOfLabelFieldWidthInTotal = 0;
    }

    public void setLabelFieldWidth(int labelFieldWidth) {
        this.labelFieldWidth = labelFieldWidth;
    }

    public void setRateOfLabelFieldWidthInTotal(float rateOfLabelFieldWidthInTotal) {
        this.rateOfLabelFieldWidthInTotal = rateOfLabelFieldWidthInTotal;
    }

    public void setValue(T value) {
        this.inputField.setValue(value);
    }

    public Optional<T> getValue() {
        return this.inputField.getValue();
    }

    public void clear() {
        this.inputField.clear();
    }

    protected void initLabelField(String text) {
        this.labelField = UIComponentFactory.createLabel(text);
    }

    @Override
    protected void loadChildComponentsSize() {
        final int availableWidth = getSize().width - ViewConstant.MEDIUM_RESERVE_WIDTH;
        final int availableHeight = getSize().height - ViewConstant.MEDIUM_RESERVE_HEIGHT;

        final int maxChildComponentHeight = availableHeight - VERTICAL_GAP;

        if (this.rateOfLabelFieldWidthInTotal > 0) {
            this.labelFieldWidth = (int) (this.rateOfLabelFieldWidthInTotal * availableHeight);
        }
        this.childComponentSizeMap.put(this.labelField, new Dimension(this.labelFieldWidth, maxChildComponentHeight));

        final int inputFieldWidth = availableWidth - HORIZONTAL_GAP - this.labelFieldWidth - HORIZONTAL_GAP;
        this.childComponentSizeMap.put(this.inputField, new Dimension(inputFieldWidth, maxChildComponentHeight));
    }

    @Override
    protected void setNotResizableChildComponents() {
    }
}
