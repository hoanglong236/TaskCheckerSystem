package org.swing.app.view.components.form.components.input;

import org.swing.app.view.components.SimpleComponent;
import org.swing.app.view.components.form.components.InputComponent;

import javax.swing.text.JTextComponent;
import java.awt.event.KeyListener;
import java.util.Optional;

public abstract class AllowTypingInputComponent extends SimpleComponent implements InputComponent<String> {

    @Override
    public void setValue(String value) {
        if (value == null) {
            clear();
            return;
        }
        ((JTextComponent) this.sourceComponent).setText(value);
    }

    @Override
    public Optional<String> getValue() {
        final String inputValue = ((JTextComponent) this.sourceComponent).getText();
        return Optional.ofNullable(inputValue);
    }

    @Override
    public void clear() {
        ((JTextComponent) this.sourceComponent).setText("");
    }

    public void addKeyListener(KeyListener keyListener) {
        this.sourceComponent.addKeyListener(keyListener);
    }

    public void removeKeyListener(KeyListener keyListener) {
        this.sourceComponent.removeKeyListener(keyListener);
    }
}
