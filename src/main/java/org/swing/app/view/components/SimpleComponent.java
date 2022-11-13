package org.swing.app.view.components;

import java.awt.Dimension;

public abstract class SimpleComponent extends ViewComponentBase {

    @Override
    public void resize(Dimension dimension) {
        this.component.setPreferredSize(dimension);
    }
}
