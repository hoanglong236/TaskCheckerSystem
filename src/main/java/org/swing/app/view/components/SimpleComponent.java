package org.swing.app.view.components;

import org.swing.app.view.components.factory.JComponentFactory;
import org.swing.app.view.components.factory.JComponentFactoryImpl;

import javax.swing.JComponent;
import javax.swing.border.Border;
import java.awt.Dimension;

public abstract class SimpleComponent extends ViewComponentBase {

    protected static final JComponentFactory JCOMPONENT_FACTORY = new JComponentFactoryImpl();

    @Override
    public void resize(Dimension dimension) {
        this.component.setPreferredSize(dimension);
    }

    public void setOpaque(boolean opaque) {
        ((JComponent) this.component).setOpaque(opaque);
    }

    public void setBorder(Border border) {
        ((JComponent) this.component).setBorder(border);
    }
}
