package org.swing.app.view.components.ui;

import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.PanelWrapperComponent;
import org.swing.app.view.components.ViewComponent;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Iterator;

public class VerticalViewportView extends PanelWrapperComponent {

    private static final FlowLayout MAIN_LAYOUT = new FlowLayout(FlowLayout.LEFT,
            ViewConstant.SMALL_H_GAP, ViewConstant.SMALL_V_GAP);

    protected VerticalViewportView() {
        super();
        this.component.setLayout(MAIN_LAYOUT);
    }

    public int getPreferChildComponentWidth() {
        final int availableWidth = getSize().width - ViewConstant.SMALL_RESERVE_WIDTH;
        return availableWidth - MAIN_LAYOUT.getHgap();
    }

    @Override
    protected void loadChildComponentsSize() {
        final int preferChildComponentWidth = getPreferChildComponentWidth();
        final Iterator<ViewComponent> childComponentIterator = getChildComponentIterator();

        while (childComponentIterator.hasNext()) {
            final ViewComponent component = childComponentIterator.next();
            int componentWidth = component.getSize().width;
            final int componentHeight = component.getSize().height;

            if (componentWidth > preferChildComponentWidth) {
                componentWidth = preferChildComponentWidth;
            }

            this.childComponentSizeMap.replace(component, new Dimension(componentWidth, componentHeight));
        }
    }

    @Override
    protected void setNotResizableChildComponents() {
    }
}
