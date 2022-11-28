package org.swing.app.view.components.form.components.input;

import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.SimpleComponent;
import org.swing.app.view.components.factory.JComponentFactory;

import javax.swing.JLabel;
import java.awt.Font;

class LabelField extends SimpleComponent implements InputComponent {
    private static final Font TEXT_FIELD_FONT = new Font(ViewConstant.PRIMARY_FONT_NAME,
            Font.PLAIN, ViewConstant.INPUT_TEXT_FONT_SIZE);

    public LabelField(String initValue) {
        super();
        this.component = JComponentFactory.createJLabel();
        this.component.setFont(TEXT_FIELD_FONT);
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
        ((JLabel) this.component).setText((String) value);
    }

    @Override
    public Object getValue() {
        final String inputValue = ((JLabel) this.component).getText();
        return inputValue == null ? null : inputValue.trim();
    }

    @Override
    public void clear() {
        ((JLabel) this.component).setText("");
    }
}