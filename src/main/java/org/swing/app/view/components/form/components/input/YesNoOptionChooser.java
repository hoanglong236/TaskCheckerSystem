package org.swing.app.view.components.form.components.input;

import org.swing.app.view.common.LayoutGapConstants;
import org.swing.app.view.common.ReserveSizeConstants;
import org.swing.app.view.components.PanelWrapperComponent;
import org.swing.app.view.components.factory.UIComponentFactory;
import org.swing.app.view.components.group.ButtonGrouper;
import org.swing.app.view.components.ui.button.RadioButton;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;

public class YesNoOptionChooser extends PanelWrapperComponent implements InputComponent<Boolean> {

    private static final byte HORIZONTAL_GAP = LayoutGapConstants.LARGE_H_GAP;
    private static final byte VERTICAL_GAP = LayoutGapConstants.SMALL_V_GAP;
    private static final LayoutManager MAIN_LAYOUT = new FlowLayout(FlowLayout.LEFT, HORIZONTAL_GAP, VERTICAL_GAP);

    private RadioButton yesRadioButton;
    private RadioButton noRadioButton;

    private final ButtonGrouper buttonGrouper = new ButtonGrouper();

    public YesNoOptionChooser() {
        super();
        setLayout(MAIN_LAYOUT);
        init();
        setDefaultValue();
    }

    private void initYesRadioButton() {
        this.yesRadioButton = UIComponentFactory.createRadioButton("Yes");
    }

    private void initNoRadioButton() {
        this.noRadioButton = UIComponentFactory.createRadioButton("No");
    }

    private void init() {
        initYesRadioButton();
        addChildComponent(this.yesRadioButton);

        initNoRadioButton();
        addChildComponent(this.noRadioButton);

        this.buttonGrouper.group(this.yesRadioButton);
        this.buttonGrouper.ungroup(this.noRadioButton);
    }

    @Override
    protected void loadChildComponentsSize() {
        final int availableHeight = getSize().height - ReserveSizeConstants.SMALL_RESERVE_HEIGHT;
        final int maxChildComponentHeight = availableHeight - VERTICAL_GAP;

        final int yesRadioButtonWidth = 60;
        this.childComponentSizeMap.put(this.yesRadioButton,
                new Dimension(yesRadioButtonWidth, maxChildComponentHeight));

        final int noRadioButtonWidth = 50;
        this.childComponentSizeMap.put(this.noRadioButton,
                new Dimension(noRadioButtonWidth, maxChildComponentHeight));
    }

    @Override
    public void setValue(Boolean value) {
        if (value == null) {
            clear();
            return;
        }
        if (value) {
            this.yesRadioButton.setSelected(true);
        } else {
            this.noRadioButton.setSelected(true);
        }
    }

    @Override
    public Boolean getValue() {
        if (this.yesRadioButton.isSelected()) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    private void setDefaultValue() {
        this.noRadioButton.setSelected(true);
    }

    @Override
    public void clear() {
        setDefaultValue();
    }
}