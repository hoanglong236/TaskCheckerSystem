package org.swing.app.view.components;

import org.swing.app.common.ArrayIterator;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ComponentListener;
import java.awt.event.MouseListener;
import java.util.Iterator;

public abstract class ViewComponentBase implements ViewComponent {

    protected WrapperComponent parent;
    protected Container sourceComponent = null;
    private boolean resizable = true;

    @Override
    public WrapperComponent getParent() {
        return parent;
    }

    @Override
    public void setParent(WrapperComponent parent) {
        this.parent = parent;
    }

    @Override
    public Component getSourceComponent() {
        return this.sourceComponent;
    }

    public boolean isVisible() {
        return this.sourceComponent.isVisible();
    }

    public void setVisible(boolean visible) {
        this.sourceComponent.setVisible(visible);
    }

    public boolean isResizable() {
        return resizable;
    }

    public void setResizable(boolean resizable) {
        this.resizable = resizable;
    }

    public abstract void resize(Dimension dimension);

    public Dimension getSize() {
        return this.sourceComponent.getPreferredSize();
    }

    public void addComponentListener(ComponentListener componentListener) {
        this.sourceComponent.addComponentListener(componentListener);
    }

    public void removeComponentListener(ComponentListener componentListener) {
        this.sourceComponent.removeComponentListener(componentListener);
    }

    private ComponentListener[] getComponentListeners() {
        return this.sourceComponent.getComponentListeners();
    }

    private void removeComponentListeners(ComponentListener[] componentListeners) {
        final Iterator<ComponentListener> componentListenerIterator = new ArrayIterator<>(componentListeners);

        while (componentListenerIterator.hasNext()) {
            final ComponentListener componentListener = componentListenerIterator.next();
            removeComponentListener(componentListener);
        }
    }

    public void addMouseListener(MouseListener mouseListener) {
        this.sourceComponent.addMouseListener(mouseListener);
    }

    public void removeMouseListener(MouseListener mouseListener) {
        this.sourceComponent.removeMouseListener(mouseListener);
    }

    private MouseListener[] getMouseListeners() {
        return this.sourceComponent.getMouseListeners();
    }

    private void removeMouseListeners(MouseListener[] mouseListeners) {
        final Iterator<MouseListener> mouseListenerIterator = new ArrayIterator<>(mouseListeners);

        while (mouseListenerIterator.hasNext()) {
            final MouseListener mouseListener = mouseListenerIterator.next();
            removeMouseListener(mouseListener);
        }
    }

    public void setBackgroundColor(Color color) {
        this.sourceComponent.setBackground(color);
    }

    @Override
    public void cancelAllEventListeners() {
        removeComponentListeners(getComponentListeners());
        removeMouseListeners(getMouseListeners());
    }

    @Override
    public boolean requestFocusInWindow() {
        return this.sourceComponent.requestFocusInWindow();
    }

    @Override
    public void setFont(Font font) {
        this.sourceComponent.setFont(font);
    }
}
