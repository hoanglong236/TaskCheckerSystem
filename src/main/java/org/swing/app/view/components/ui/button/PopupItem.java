package org.swing.app.view.components.ui.button;

import org.swing.app.view.components.factory.JComponentFactory;

import javax.swing.JComponent;

public class PopupItem extends Button {

    public static final byte NORMAL_TYPE_POPUP_ITEM = 0;
    public static final byte RADIO_BUTTON_TYPE_POPUP_ITEM = 1;
    public static final byte CHECK_BOX_TYPE_POPUP_ITEM = 2;

    public PopupItem(byte type, String itemText) {
        super();
        this.sourceComponent = getPopupItemComponentByType(type);
        setText(itemText);
    }

    private JComponent getPopupItemComponentByType(byte type) {
        switch (type) {
            case NORMAL_TYPE_POPUP_ITEM:
                return JComponentFactory.createJMenuItem();
            case RADIO_BUTTON_TYPE_POPUP_ITEM:
                return JComponentFactory.createJRadioButtonMenuItem();
            case CHECK_BOX_TYPE_POPUP_ITEM:
                return JComponentFactory.createJCheckBoxMenuItem();
            default:
                throw new IllegalArgumentException();
        }
    }
}
