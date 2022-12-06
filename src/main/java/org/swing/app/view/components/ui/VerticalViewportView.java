package org.swing.app.view.components.ui;

import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.PanelWrapperComponent;

import java.awt.Dimension;
import java.awt.FlowLayout;

public abstract class VerticalViewportView extends PanelWrapperComponent {

    protected static final FlowLayout MAIN_LAYOUT = new FlowLayout(FlowLayout.LEFT,
            ViewConstant.SMALL_H_GAP, ViewConstant.SMALL_V_GAP);

    protected int preferWidth;
    protected int preferHeight;

    public VerticalViewportView() {
        super();
        setLayout(MAIN_LAYOUT);
        this.preferWidth = 0;
        this.preferHeight = ViewConstant.SMALL_RESERVE_HEIGHT;
    }

    public int getPreferChildComponentWidth() {
        final int availableWidth = this.preferWidth - ViewConstant.SMALL_RESERVE_WIDTH;
        return availableWidth - MAIN_LAYOUT.getHgap();
    }

    @Override
    protected abstract void loadChildComponentsSize();

    @Override
    public void resize(Dimension dimension) {
        throw new UnsupportedOperationException();
    }

    public abstract void resizeWidth(int width);
}
