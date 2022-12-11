package org.swing.app.view.components.ui;

import org.swing.app.view.components.SimpleComponent;
import org.swing.app.view.components.factory.JComponentFactory;
import org.swing.app.view.util.ViewUtil;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Label extends SimpleComponent {

    public Label(String text, String iconLocation) {
        this.component = JComponentFactory.createJLabel();
        setText(text);
        setIcon(iconLocation);
    }

    public Label(String text) {
        this.component = JComponentFactory.createJLabel();
        setText(text);
    }

    public void setIcon(String iconLocation) {
        final byte iconWidth = 15;
        final byte iconHeight = 15;
        final ImageIcon imageIcon = ViewUtil.getImageIcon(iconLocation, iconWidth, iconHeight);

        ((JLabel) this.component).setIcon(imageIcon);
    }

    public void setText(String text) {
        ((JLabel) this.component).setText(text);
    }
}
