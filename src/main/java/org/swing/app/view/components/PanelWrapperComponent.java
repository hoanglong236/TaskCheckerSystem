package org.swing.app.view.components;

import org.swing.app.view.components.factory.JComponentFactory;
import org.swing.app.view.components.ui.Popup;

import javax.swing.JPanel;

public abstract class PanelWrapperComponent extends WrapperComponent {

    public PanelWrapperComponent() {
        this.sourceComponent = JComponentFactory.createJPanel();
    }

    protected void setPopup(Popup popup) {
        ((JPanel) this.sourceComponent).setComponentPopupMenu(popup.getPopupMenu());
    }

    public void setOpaque(boolean opaque) {
        ((JPanel) this.sourceComponent).setOpaque(opaque);
    }

    @Override
    public void refreshUI() {
        this.sourceComponent.revalidate();
        this.sourceComponent.repaint();
    }

    @Override
    public ViewComponent getWindowComponent() {
        if (this.parent == null) {
            throw new UnsupportedOperationException();
        }
        return this.parent.getWindowComponent();
    }
}
