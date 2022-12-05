package org.swing.app.view.components.form.components.input;

import java.awt.event.KeyListener;

public interface AllowTypingComponent {

    void addKeyListener(KeyListener keyListener);
    void removeKeyListener(KeyListener keyListener);
}
