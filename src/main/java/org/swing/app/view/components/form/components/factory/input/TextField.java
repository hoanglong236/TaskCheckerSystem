package org.swing.app.view.components.form.components.factory.input;

import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.SimpleComponent;
import org.swing.app.view.components.form.components.InputComponent;

import javax.swing.JTextField;
import java.awt.Font;

class TextField extends SimpleComponent implements InputComponent {

    private static final Font TEXT_FIELD_FONT = new Font(ViewConstant.PRIMARY_FONT_NAME,
            Font.PLAIN, ViewConstant.INPUT_TEXT_FONT_SIZE);

    public TextField(String initValue) {
        super();
        this.component = new JTextField();
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
        ((JTextField) this.component).setText((String) value);
    }

    @Override
    public Object getValue() {
        final String inputValue = ((JTextField) this.component).getText();
        return inputValue == null ? null : inputValue.trim();
    }

    @Override
    public void clear() {
        ((JTextField) this.component).setText("");
    }
}
