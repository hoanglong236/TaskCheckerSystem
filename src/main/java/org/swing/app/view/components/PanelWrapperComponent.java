package org.swing.app.view.components;

import org.swing.app.view.components.factory.JComponentFactory;
import org.swing.app.view.components.ui.Popup;

import javax.swing.JPanel;

public abstract class PanelWrapperComponent extends WrapperComponent {

    public PanelWrapperComponent() {
        this.component = JComponentFactory.createJPanel();
    }

    protected void setPopup(Popup popup) {
        ((JPanel) this.component).setComponentPopupMenu(popup.getPopupMenu());
    }

    public void setOpaque(boolean opaque) {
        ((JPanel) this.component).setOpaque(opaque);
    }
}
