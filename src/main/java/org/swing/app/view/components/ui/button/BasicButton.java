package org.swing.app.view.components.ui.button;

import org.swing.app.view.common.ViewConstants;
import org.swing.app.view.components.factory.JComponentFactory;

import java.awt.Font;

public class BasicButton extends Button {

    private static final Font DEFAULT_BUTTON_FONT = new Font(ViewConstants.PRIMARY_FONT_NAME, Font.BOLD,
            ViewConstants.DEFAULT_BUTTON_FONT_SIZE);

    public BasicButton(String text) {
        this.sourceComponent = JComponentFactory.createJButton();
        setText(text);
        setFont(DEFAULT_BUTTON_FONT);
    }
}
