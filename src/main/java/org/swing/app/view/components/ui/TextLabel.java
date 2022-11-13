package org.swing.app.view.components.ui;

public class TextLabel extends Label {

    public TextLabel(String labelText) {
        super(labelText);
    }

    public void update(String labelText) {
        setText(labelText);
    }
}
