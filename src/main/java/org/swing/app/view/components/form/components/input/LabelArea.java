package org.swing.app.view.components.form.components.input;

import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.SimpleComponent;
import org.swing.app.view.components.factory.JComponentFactory;
import org.swing.app.view.components.form.components.InputComponent;

import javax.swing.BorderFactory;
import javax.swing.JTextArea;
import java.awt.Font;

public class LabelArea extends SimpleComponent implements InputComponent {

    private static final Font TEXT_FIELD_FONT = new Font(ViewConstant.PRIMARY_FONT_NAME,
            Font.PLAIN, ViewConstant.INPUT_TEXT_FONT_SIZE);

    public LabelArea(String initValue) {
        super();
        this.component = JComponentFactory.createJTextArea();
        this.component.setFont(TEXT_FIELD_FONT);
        setOpaque(true);
        setBorder(BorderFactory.createEmptyBorder());
        ((JTextArea) this.component).setEditable(false);
        setValue(initValue);
    }

    @Override
    public void setValue(Object value) {
        if (value == null) {
            clear();
            return;
        }
        if (!(value instanceof String)) {
            throw new IllegalArgumentException();
        }
        ((JTextArea) this.component).setText((String) value);
    }

    @Override
    public Object getValue() {
        final String inputValue = ((JTextArea) this.component).getText();
        return inputValue == null ? null : inputValue.trim();
    }

    @Override
    public void clear() {
        ((JTextArea) this.component).setText("");
    }
}
