package org.swing.app.view.components.ui;

import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.ViewComponentBase;
import org.swing.app.view.components.WrapperComponent;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Iterator;

public class VerticalViewportView extends WrapperComponent {

    private static final FlowLayout MAIN_LAYOUT = new FlowLayout(FlowLayout.LEFT,
            ViewConstant.SMALL_H_GAP, ViewConstant.SMALL_V_GAP);

    public VerticalViewportView() {
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
        final Iterator<ViewComponentBase> childComponentIterator = getChildComponentIterator();

        while (childComponentIterator.hasNext()) {
            final ViewComponentBase component = childComponentIterator.next();
            int componentWidth = component.getSize().width;
            final int componentHeight = component.getSize().height;

            if (componentWidth > preferChildComponentWidth) {
                componentWidth = preferChildComponentWidth;
            }

            this.childComponentSizeMap.replace(component, new Dimension(componentWidth, componentHeight));
        }
    }
}
