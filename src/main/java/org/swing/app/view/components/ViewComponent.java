package org.swing.app.view.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ComponentListener;
import java.awt.event.MouseListener;

public interface ViewComponent {

    /**
     * TODO: comment this
     * @return
     */
    Component getSourceComponent();

    WrapperComponent getParent();
    void setParent(WrapperComponent parent);

    void setVisible(boolean visible);
    boolean isVisible();

    void resize(Dimension dimension);
    Dimension getSize();

    void addComponentListener(ComponentListener componentListener);
    void removeComponentListener(ComponentListener componentListener);

    void addMouseListener(MouseListener mouseListener);
    void removeMouseListener(MouseListener mouseListener);

    void cancelAllEventListeners();
    void setBackgroundColor(Color color);

    void setForegroundColor(Color color);

    boolean requestFocusInWindow();

    void setFont(Font font);
}
