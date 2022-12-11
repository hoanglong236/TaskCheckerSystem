package org.swing.app.view.components.form.components.input;

import org.swing.app.view.components.SimpleComponent;
import org.swing.app.view.components.form.components.InputComponent;

import javax.swing.text.JTextComponent;
import java.awt.event.KeyListener;

public abstract class AllowTypingInputComponent extends SimpleComponent implements InputComponent {

    @Override
    public void setValue(Object value) {
        if (value == null) {
            clear();
            return;
        }
        if (!(value instanceof String)) {
            throw new IllegalArgumentException();
        }
        ((JTextComponent) this.component).setText((String) value);
    }

    @Override
    public Object getValue() {
        final String inputValue = ((JTextComponent) this.component).getText();
        return inputValue == null ? null : inputValue.trim();
    }

    @Override
    public void clear() {
        ((JTextComponent) this.component).setText("");
    }

    public void addKeyListener(KeyListener keyListener) {
        this.component.addKeyListener(keyListener);
    }

    public void removeKeyListener(KeyListener keyListener) {
        this.component.removeKeyListener(keyListener);
    }
}
