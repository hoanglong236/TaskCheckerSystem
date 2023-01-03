package org.swing.app.view.components.ui;

import org.swing.app.view.components.SimpleComponent;
import org.swing.app.view.components.factory.JComponentFactory;

import javax.swing.BorderFactory;
import javax.swing.JTextArea;

public class LabelArea extends SimpleComponent {

    public LabelArea(String text) {
        super();
        this.sourceComponent = JComponentFactory.createJTextArea();
        setOpaque(true);
        setBorder(BorderFactory.createEmptyBorder());
        ((JTextArea) this.sourceComponent).setEditable(false);
        setText(text);
    }

    public void setText(String value) {
        ((JTextArea) this.sourceComponent).setText((String) value);
    }
}
