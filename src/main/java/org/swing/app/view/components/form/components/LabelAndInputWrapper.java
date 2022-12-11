package org.swing.app.view.components.form.components;

import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.PanelWrapperComponent;
import org.swing.app.view.components.form.components.factory.InputComponentFactory;

import java.awt.Dimension;
import java.awt.FlowLayout;

public abstract class LabelAndInputWrapper extends PanelWrapperComponent {

    private static final FlowLayout MAIN_LAYOUT = new FlowLayout(FlowLayout.LEFT,
            ViewConstant.MEDIUM_H_GAP, ViewConstant.MEDIUM_V_GAP);

    protected InputComponent labelField;
    protected InputComponent inputField;

    private int labelFieldWidth;
    private float rateOfLabelFieldWidthInTotal;

    public LabelAndInputWrapper() {
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

    protected void initLabelField(String labelText) {
        this.labelField = InputComponentFactory.createLabelField(labelText);
    }

    public void setValue(Object value) {
        this.inputField.setValue(value);
    }

    public Object getValue() {
        return this.inputField.getValue();
    }

    public void clear() {
        this.inputField.clear();
    }

    @Override
    protected void loadChildComponentsSize() {
        this.childComponentSizeMap.clear();

        final int availableWidth = getSize().width - ViewConstant.MEDIUM_RESERVE_WIDTH;
        final int availableHeight = getSize().height - ViewConstant.MEDIUM_RESERVE_HEIGHT;

        final int maxChildComponentHeight = availableHeight - MAIN_LAYOUT.getVgap();

        if (this.rateOfLabelFieldWidthInTotal > 0) {
            this.labelFieldWidth = (int) (this.rateOfLabelFieldWidthInTotal * availableHeight);
        }
        this.childComponentSizeMap.put(this.labelField, new Dimension(this.labelFieldWidth, maxChildComponentHeight));

        final int inputFieldWidth = availableWidth - MAIN_LAYOUT.getHgap()
                - this.labelFieldWidth - MAIN_LAYOUT.getHgap();
        this.childComponentSizeMap.put(this.inputField, new Dimension(inputFieldWidth, maxChildComponentHeight));
    }

    @Override
    protected void setNotResizableChildComponents() {
    }
}
