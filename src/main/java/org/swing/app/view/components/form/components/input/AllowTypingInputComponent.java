package org.swing.app.view.components.form.components.input;

import org.swing.app.view.components.SimpleComponent;

import javax.swing.text.JTextComponent;
import java.awt.event.KeyListener;

public abstract class AllowTypingInputComponent extends SimpleComponent implements InputComponent<String> {

    protected void setDefaultValue() {
        final String emptyText = "";
        ((JTextComponent) this.sourceComponent).setText(emptyText);
    }

    @Override
    public void setValue(String value) {
        if (value == null) {
            clear();
            return;
        }
        ((JTextComponent) this.sourceComponent).setText(value);
    }

    @Override
    public String getValue() {
        return ((JTextComponent) this.sourceComponent).getText().trim();
    }

    @Override
    public void clear() {
        setDefaultValue();
    }

    public void addKeyListener(KeyListener keyListener) {
        this.sourceComponent.addKeyListener(keyListener);
    }

    public void removeKeyListener(KeyListener keyListener) {
        this.sourceComponent.removeKeyListener(keyListener);
    }
}
