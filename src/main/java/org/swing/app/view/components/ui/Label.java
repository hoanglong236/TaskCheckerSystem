package org.swing.app.view.components.ui;

import org.swing.app.view.components.SimpleComponent;
import org.swing.app.view.components.factory.JComponentFactory;
import org.swing.app.view.util.ViewUtil;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Label extends SimpleComponent {

    protected Label(String iconLocation, String labelText) {
        this.component = JComponentFactory.createJLabel();
        setIcon(iconLocation);
        setText(labelText);
    }

    protected Label(String labelText) {
        this.component = JComponentFactory.createJLabel();
        setText(labelText);
    }

    protected void setIcon(String iconLocation) {
        final byte iconWidth = 15;
        final byte iconHeight = 15;
        final ImageIcon imageIcon = ViewUtil.getImageIcon(iconLocation, iconWidth, iconHeight);

        ((JLabel) this.component).setIcon(imageIcon);
    }

    protected void setText(String text) {
        ((JLabel) this.component).setText(text);
    }
}
