package org.swing.app.view.components.factory;

import org.swing.app.view.components.ui.*;
import org.swing.app.view.components.ui.button.BasicButton;
import org.swing.app.view.components.ui.button.CheckBox;
import org.swing.app.view.components.ui.button.PopupItem;
import org.swing.app.view.components.ui.button.RadioButton;
import org.swing.app.view.components.ui.label.ActivationLabel;
import org.swing.app.view.components.ui.label.CompletionRateLabel;
import org.swing.app.view.components.ui.label.CountDownLabel;
import org.swing.app.view.components.ui.label.Label;

import java.time.LocalDateTime;

public class UIComponentFactory {

    public static BasicButton createBasicButton(String text) {
        return new BasicButton(text);
    }

    public static CheckBox createCheckBox(String text) {
        return new CheckBox(text);
    }

    public static RadioButton createRadioButton(String text) {
        return new RadioButton(text);
    }

    public static PopupItem createPopupItem(String itemName) {
        return new PopupItem(PopupItem.NORMAL_TYPE_POPUP_ITEM, itemName);
    }

    public static PopupItem createRadioButtonPopupItem(String itemName) {
        return new PopupItem(PopupItem.RADIO_BUTTON_TYPE_POPUP_ITEM, itemName);
    }

    public static PopupItem createCheckBoxPopupItem(String itemName) {
        return new PopupItem(PopupItem.CHECK_BOX_TYPE_POPUP_ITEM, itemName);
    }

    public static Label createLabel(String text) {
        return new Label(text);
    }

    public static ActivationLabel createActivationLabel() {
        return new ActivationLabel();
    }

    public static CompletionRateLabel createCompletionRateLabel(int completedCount, int totalCount) {
        return new CompletionRateLabel(completedCount, totalCount);
    }

    public static CountDownLabel createCountDownLabel(LocalDateTime deadline) {
        return new CountDownLabel(deadline);
    }

    public static LabelArea createLabelArea(String text) {
        return new LabelArea(text);
    }

    public static VerticalScrollPane createVerticalScrollPane() {
        return new VerticalScrollPane();
    }

    public static Popup createPopup() {
        return new Popup();
    }
}