package org.swing.app.view.components.ui;

import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.SimpleComponent;
import org.swing.app.view.components.factory.JComponentFactory;

import javax.swing.BorderFactory;
import javax.swing.JTextArea;
import java.awt.Font;

public class LabelArea extends SimpleComponent {

    private static final Font TEXT_FIELD_FONT = new Font(ViewConstant.PRIMARY_FONT_NAME,
            Font.PLAIN, ViewConstant.INPUT_TEXT_FONT_SIZE);

    public LabelArea(String text) {
        super();
        this.component = JComponentFactory.createJTextArea();
        this.component.setFont(TEXT_FIELD_FONT);
        setOpaque(true);
        setBorder(BorderFactory.createEmptyBorder());
        ((JTextArea) this.component).setEditable(false);
        setText(text);
    }

    public void setText(String value) {
        ((JTextArea) this.component).setText((String) value);
    }
}
