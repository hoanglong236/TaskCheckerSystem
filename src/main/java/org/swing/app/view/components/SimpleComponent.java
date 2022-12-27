package org.swing.app.view.components;

import javax.swing.JComponent;
import javax.swing.border.Border;
import java.awt.Dimension;

public abstract class SimpleComponent extends ViewComponentBase {

    @Override
    public void resize(Dimension dimension) {
        this.sourceComponent.setPreferredSize(dimension);
    }

    public void setOpaque(boolean opaque) {
        ((JComponent) this.sourceComponent).setOpaque(opaque);
    }

    public void setBorder(Border border) {
        ((JComponent) this.sourceComponent).setBorder(border);
    }
}
