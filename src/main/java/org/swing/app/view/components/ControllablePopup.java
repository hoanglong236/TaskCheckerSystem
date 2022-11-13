package org.swing.app.view.components;

import org.swing.app.view.components.ui.Popup;

public interface ControllablePopup {

    void addPopup(Popup popup);
    void removePopup(Popup popup);
    void disposePopup();
    Popup getPopup();
}
