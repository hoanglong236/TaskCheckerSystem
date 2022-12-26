package org.swing.app.view.components.form.components.input;

import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.PanelWrapperComponent;
import org.swing.app.view.components.form.components.InputComponent;

import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.util.Optional;

// TODO: use this instead of combo box yes no
public class YesNoOptionChooser extends PanelWrapperComponent implements InputComponent<Boolean> {

    private static final byte HORIZONTAL_GAP = ViewConstant.LARGE_H_GAP;
    private static final byte VERTICAL_GAP = ViewConstant.SMALL_V_GAP;
    private static final LayoutManager MAIN_LAYOUT = new FlowLayout(FlowLayout.LEFT, HORIZONTAL_GAP, VERTICAL_GAP);

    public YesNoOptionChooser() {
        super();
        setLayout(MAIN_LAYOUT);
    }

    @Override
    protected void loadChildComponentsSize() {

    }

    @Override
    protected void setNotResizableChildComponents() {

    }

    @Override
    public void setValue(Boolean value) {

    }

    @Override
    public Optional<Boolean> getValue() {
        return Optional.empty();
    }

    @Override
    public void clear() {

    }
}
