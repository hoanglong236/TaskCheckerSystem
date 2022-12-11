package org.swing.app.view.components.form.components.input;

import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.factory.JComponentFactory;

import java.awt.Font;

public class TextArea extends AllowTypingInputComponent {

    private static final Font TEXT_FIELD_FONT = new Font(ViewConstant.PRIMARY_FONT_NAME,
            Font.PLAIN, ViewConstant.INPUT_TEXT_FONT_SIZE);

    public TextArea(String initValue) {
        super();
        this.component = JComponentFactory.createJTextArea();
        this.component.setFont(TEXT_FIELD_FONT);
        setValue(initValue);
    }
}
