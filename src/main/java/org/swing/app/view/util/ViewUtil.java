package org.swing.app.view.util;

import org.swing.app.util.MessageLoader;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.net.URL;

public class ViewUtil {

    public static ImageIcon getImageIcon(String iconLocation, short width, short height) {
        final URL iconUrl = ViewUtil.class.getClassLoader().getResource(iconLocation);
        if (iconUrl == null) {
            final MessageLoader messageLoader = MessageLoader.getInstance();
            throw new IllegalArgumentException(messageLoader.getMessage("icon.location.not.found"));
        }

        final ImageIcon originalImageIcon = new ImageIcon(iconUrl);
        final Image originalImage = originalImageIcon.getImage();
        if (originalImage == null) {
            final MessageLoader messageLoader = MessageLoader.getInstance();
            throw new IllegalArgumentException(messageLoader.getMessage("icon.location.not.valid"));
        }

        return new ImageIcon(originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH));
    }
}