package org.swing.app.view.components;

import java.awt.Dimension;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class WrapperComponent extends ViewComponentBase implements Wrapper {

    protected final Map<ViewComponent, Dimension> childComponentSizeMap = new HashMap<>();

    /**
     * store child components in order when its display in the UI
     */
    protected final List<ViewComponent> childComponents = new ArrayList<>();

    public WrapperComponent() {
    }

    protected void setLayout(LayoutManager layoutManager) {
        this.sourceComponent.setLayout(layoutManager);
    }

    @Override
    public void addChildComponent(ViewComponent childComponent) {
        addChildComponent(childComponent, -1);
    }

    @Override
    public void addChildComponent(ViewComponent childComponent, int position) {
        if (position > getChildComponentCount() || position < -1) {
            throw new IndexOutOfBoundsException();
        }

        this.sourceComponent.add(childComponent.getSourceComponent(), position);
        if (position == -1) {
            position = getChildComponentCount();
        }
        this.childComponents.add(position, childComponent);
        childComponent.setParent(this);
    }

    @Override
    public void removeChildComponent(ViewComponent childComponent) {
        this.childComponents.remove(childComponent);
        this.sourceComponent.remove(childComponent.getSourceComponent());
        childComponent.setParent(null);
    }

    @Override
    public int getChildComponentPosition(ViewComponent childComponent) {
        return this.childComponents.indexOf(childComponent);
    }

    @Override
    public int getChildComponentCount() {
        return this.childComponents.size();
    }

    protected abstract void loadChildComponentsSize();

    protected void resizeChildComponents() {
        final Iterator<ViewComponent> childComponentIterator = getChildComponentIterator();

        while (childComponentIterator.hasNext()) {
            final ViewComponent childComponent = childComponentIterator.next();

            if (childComponent.isResizable()) {
                childComponent.resize(this.childComponentSizeMap.get(childComponent));
            }
        }

        this.childComponentSizeMap.clear();
    }

    protected abstract void setNotResizableChildComponents();

    @Override
    public void resize(Dimension dimension) {
        this.sourceComponent.setPreferredSize(dimension);
        loadChildComponentsSize();
        resizeChildComponents();
        setNotResizableChildComponents();
        refreshUI();
    }

    private void cancelAllEventListenersForChildComponents() {
        final Iterator<ViewComponent> childComponentIterator = getChildComponentIterator();

        while (childComponentIterator.hasNext()) {
            final ViewComponent childComponent = childComponentIterator.next();
            childComponent.cancelAllEventListeners();
        }
    }

    @Override
    public void cancelAllEventListeners() {
        super.cancelAllEventListeners();
        cancelAllEventListenersForChildComponents();
    }

    @Override
    public Iterator<ViewComponent> getChildComponentIterator() {
        return this.childComponents.iterator();
    }

    @Override
    public void refreshUI() {
        this.sourceComponent.revalidate();
        this.sourceComponent.repaint();
    }
}
