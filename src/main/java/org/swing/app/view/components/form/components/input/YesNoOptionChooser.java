package org.swing.app.view.components.form.components.input;

import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.PanelWrapperComponent;
import org.swing.app.view.components.factory.UIComponentFactory;
import org.swing.app.view.components.form.components.InputComponent;
import org.swing.app.view.components.group.ButtonGrouper;
import org.swing.app.view.components.ui.button.RadioButton;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.util.Optional;

// TODO: use this instead of combo box yes no
public class YesNoOptionChooser extends PanelWrapperComponent implements InputComponent<Boolean> {

    private static final byte HORIZONTAL_GAP = ViewConstant.LARGE_H_GAP;
    private static final byte VERTICAL_GAP = ViewConstant.SMALL_V_GAP;
    private static final LayoutManager MAIN_LAYOUT = new FlowLayout(FlowLayout.LEFT, HORIZONTAL_GAP, VERTICAL_GAP);

    private RadioButton yesRadioButton;
    private RadioButton noRadioButton;

    private ButtonGrouper buttonGrouper;

    public YesNoOptionChooser(boolean initValue) {
        super();
        setLayout(MAIN_LAYOUT);
        this.buttonGrouper = new ButtonGrouper();
        setValue(initValue);
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
        final int availableHeight = getSize().height - ViewConstant.SMALL_RESERVE_HEIGHT;
        final int maxChildComponentHeight = availableHeight - VERTICAL_GAP;

        final int yesRadioButtonWidth = 60;
        this.childComponentSizeMap.put(this.yesRadioButton,
                new Dimension(yesRadioButtonWidth, maxChildComponentHeight));

        final int noRadioButtonWidth = 50;
        this.childComponentSizeMap.put(this.noRadioButton,
                new Dimension(noRadioButtonWidth, maxChildComponentHeight));
    }

    @Override
    protected void setNotResizableChildComponents() {
        this.yesRadioButton.setResizable(false);
        this.noRadioButton.setResizable(false);
    }

    @Override
    public void setValue(Boolean value) {
        if (value == null) {
            clear();
            return;
        }
        if (value.booleanValue()) {
            this.yesRadioButton.setSelected(true);
        } else {
            this.noRadioButton.setSelected(true);
        }
    }

    @Override
    public Optional<Boolean> getValue() {
        if (this.yesRadioButton.isSelected()) {
            return Optional.of(Boolean.TRUE);
        }
        if (this.noRadioButton.isSelected()) {
            return Optional.of(Boolean.FALSE);
        }

        return Optional.empty();
    }

    @Override
    public void clear() {
        this.noRadioButton.setSelected(true);
    }
}