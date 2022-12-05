package org.swing.app.view.components.form.components.input;

import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.SimpleComponent;
import org.swing.app.view.components.factory.JComponentFactory;

import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.KeyListener;

class TextField extends SimpleComponent implements InputComponent, AllowTypingComponent {

    private static final Font TEXT_FIELD_FONT = new Font(ViewConstant.PRIMARY_FONT_NAME,
            Font.PLAIN, ViewConstant.INPUT_TEXT_FONT_SIZE);

    public TextField(String initValue) {
        super();
        this.component = JComponentFactory.createJTextField();
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

    @Override
    public void addKeyListener(KeyListener keyListener) {
        this.component.addKeyListener(keyListener);
    }

    @Override
    public void removeKeyListener(KeyListener keyListener) {
        this.component.removeKeyListener(keyListener);
    }
}
