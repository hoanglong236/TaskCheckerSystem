package org.swing.app.view.components;

import org.swing.app.common.ArrayIterator;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ComponentListener;
import java.awt.event.MouseListener;
import java.util.Iterator;

public abstract class ViewComponentBase implements ViewComponent {

    protected Container component = null;
    private boolean resizable = true;

    @Override
    public Component getComponent() {
        return this.component;
    }

    public boolean isVisible() {
        return this.component.isVisible();
    }

    public void setVisible(boolean visible) {
        this.component.setVisible(visible);
    }

    public boolean isResizable() {
        return resizable;
    }

    public void setResizable(boolean resizable) {
        this.resizable = resizable;
    }

    public abstract void resize(Dimension dimension);

    public Dimension getSize() {
        return this.component.getPreferredSize();
    }

    public void addComponentListener(ComponentListener componentListener) {
        this.component.addComponentListener(componentListener);
    }

    public void removeComponentListener(ComponentListener componentListener) {
        this.component.removeComponentListener(componentListener);
    }

    private ComponentListener[] getComponentListeners() {
        return this.component.getComponentListeners();
    }

    private void removeComponentListeners(ComponentListener[] componentListeners) {
        final Iterator<ComponentListener> componentListenerIterator = new ArrayIterator<>(componentListeners);

        while (componentListenerIterator.hasNext()) {
            final ComponentListener componentListener = componentListenerIterator.next();
            removeComponentListener(componentListener);
        }
    }

    public void addMouseListener(MouseListener mouseListener) {
        this.component.addMouseListener(mouseListener);
    }

    public void removeMouseListener(MouseListener mouseListener) {
        this.component.removeMouseListener(mouseListener);
    }

    private MouseListener[] getMouseListeners() {
        return this.component.getMouseListeners();
    }

    private void removeMouseListeners(MouseListener[] mouseListeners) {
        final Iterator<MouseListener> mouseListenerIterator = new ArrayIterator<>(mouseListeners);

        while (mouseListenerIterator.hasNext()) {
            final MouseListener mouseListener = mouseListenerIterator.next();
            removeMouseListener(mouseListener);
        }
    }

    public void setBackgroundColor(Color color) {
        this.component.setBackground(color);
    }

    public void dispose() {
        removeComponentListeners(getComponentListeners());
        removeMouseListeners(getMouseListeners());
    }

    @Override
    public boolean requestFocusInWindow() {
        return this.component.requestFocusInWindow();
    }
}
