package org.swing.app.view.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ComponentListener;
import java.awt.event.MouseListener;

public interface ViewComponent {

    Component getComponent();

    void setVisible(boolean visible);
    boolean isVisible();

    void setResizable(boolean resizable);
    boolean isResizable();
    void resize(Dimension dimension);
    Dimension getSize();

    void addComponentListener(ComponentListener componentListener);
    void removeComponentListener(ComponentListener componentListener);

    void addMouseListener(MouseListener mouseListener);
    void removeMouseListener(MouseListener mouseListener);

    void dispose();
    void setBackgroundColor(Color color);
}