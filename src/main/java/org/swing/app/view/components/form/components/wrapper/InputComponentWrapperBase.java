package org.swing.app.view.components.form.components.wrapper;

import org.swing.app.view.common.ComponentSizeConstants;
import org.swing.app.view.common.LayoutGapConstants;
import org.swing.app.view.common.ViewConstants;
import org.swing.app.view.components.PanelWrapperComponent;
import org.swing.app.view.components.SimpleComponent;
import org.swing.app.view.components.factory.UIComponentFactory;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.LayoutManager;

public abstract class InputComponentWrapperBase<T> extends PanelWrapperComponent implements InputComponentWrapper<T> {

    protected static final byte HORIZONTAL_GAP = LayoutGapConstants.MEDIUM_H_GAP;
    protected static final byte VERTICAL_GAP = LayoutGapConstants.MEDIUM_V_GAP;
    protected static final LayoutManager MAIN_LAYOUT = new FlowLayout(FlowLayout.LEFT, HORIZONTAL_GAP, VERTICAL_GAP);

    protected static final Font DEFAULT_LABEL_FIELD_FONT = new Font(ViewConstants.PRIMARY_FONT_NAME,
            Font.PLAIN, ViewConstants.DEFAULT_LABEL_FIELD_FONT_SIZE);

    protected static final Font DEFAULT_INPUT_FIELD_FONT = new Font(ViewConstants.PRIMARY_FONT_NAME,
            Font.PLAIN, ViewConstants.DEFAULT_INPUT_FIELD_FONT_SIZE);

    protected SimpleComponent labelField;

    protected int labelFieldWidth = ComponentSizeConstants.DEFAULT_LABEL_FIELD_WIDTH;
    protected float rateOfLabelFieldWidthInTotal = 0;

    public InputComponentWrapperBase() {
        super();
        setLayout(MAIN_LAYOUT);
    }

    @Override
    public void setLabelFieldWidth(int labelFieldWidth) {
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
