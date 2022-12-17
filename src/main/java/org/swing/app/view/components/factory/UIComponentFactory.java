package org.swing.app.view.components.factory;

import org.swing.app.view.components.PanelWrapperComponent;
import org.swing.app.view.components.ui.*;

import java.time.LocalDateTime;

public class UIComponentFactory {

    public static ActivationLabel createActivationLabel() {
        return new ActivationLabel();
    }

    public static Checker createChecker(boolean checked) {
        return new Checker(checked);
    }

    public static CompletionRateLabel createCompletionRateLabel(int completedCount, int totalCount) {
        return new CompletionRateLabel(completedCount, totalCount);
    }

    public static DeadlineLabel createDeadlineLabel(LocalDateTime startDatetime, LocalDateTime finishDatetime) {
        if (startDatetime == null || finishDatetime == null) {
            throw new IllegalArgumentException();
        }
        return new DeadlineLabel(startDatetime, finishDatetime);
    }

    public static Button createButton(String text) {
        return new Button(text);
    }

    public static Button createButton(String text, String iconLocation) {
        return new Button(text, iconLocation);
    }

    public static Label createLabel(String text) {
        return new Label(text);
    }

    public static Label createLabel(String text, String iconLocation) {
        return new Label(text, iconLocation);
    }

    public static Popup createPopup() {
        return new Popup();
    }

    public static PopupItem createPopupItem(String itemName) {
        return new PopupItem(itemName);
    }

    public static VerticalScrollPane createVerticalScrollPane(PanelWrapperComponent viewportView) {
        if (viewportView == null || !(viewportView instanceof VerticalViewportView)) {
            throw new IllegalArgumentException();
        }
        return new VerticalScrollPane(viewportView);
    }
}