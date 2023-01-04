package org.swing.app.view.components.form.components;

import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.PanelWrapperComponent;
import org.swing.app.view.components.SimpleComponent;
import org.swing.app.view.components.factory.UIComponentFactory;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.LayoutManager;

public abstract class InputComponentWrapperBase<T> extends PanelWrapperComponent implements InputComponentWrapper<T> {

    protected static final byte HORIZONTAL_GAP = ViewConstant.MEDIUM_H_GAP;
    protected static final byte VERTICAL_GAP = ViewConstant.MEDIUM_V_GAP;
    protected static final LayoutManager MAIN_LAYOUT = new FlowLayout(FlowLayout.LEFT, HORIZONTAL_GAP, VERTICAL_GAP);

    protected static final Font DEFAULT_LABEL_FIELD_FONT = new Font(ViewConstant.PRIMARY_FONT_NAME,
            Font.PLAIN, ViewConstant.LABEL_FIELD_FONT_SIZE);

    protected static final Font DEFAULT_INPUT_FIELD_FONT = new Font(ViewConstant.PRIMARY_FONT_NAME,
            Font.PLAIN, ViewConstant.INPUT_FIELD_FONT_SIZE);

    protected SimpleComponent labelField;

    protected short labelFieldWidth;
    protected float rateOfLabelFieldWidthInTotal;

    public InputComponentWrapperBase() {
        super();
        setLayout(MAIN_LAYOUT);
        this.labelFieldWidth = ViewConstant.DEFAULT_LABEL_WIDTH;
        this.rateOfLabelFieldWidthInTotal = 0;
    }

    @Override
    public void setLabelFieldWidth(short labelFieldWidth) {
        this.labelFieldWidth = labelFieldWidth;
    }

    @Override
    public void setRateOfLabelFieldWidthInTotal(float rateOfLabelFieldWidthInTotal) {
        this.rateOfLabelFieldWidthInTotal = rateOfLabelFieldWidthInTotal;
    }

    protected void initLabelField(String text) {
        this.labelField = UIComponentFactory.createLabel(text);
        this.labelField.setFont(DEFAULT_LABEL_FIELD_FONT);
    }
}
