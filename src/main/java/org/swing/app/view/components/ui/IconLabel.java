package org.swing.app.view.components.ui;

public class IconLabel extends Label {

    public IconLabel(String iconLocation) {
        super(iconLocation, "");
    }

    // TODO: review this if use update to set icon
    public void update(String iconLocation) {
        setIcon(iconLocation);
    }
}
