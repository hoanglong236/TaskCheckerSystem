package org.swing.app.view.components;

import java.awt.Dimension;
import java.awt.LayoutManager;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public abstract class WrapperComponent extends ViewComponentBase implements Wrapper {

    protected Map<ViewComponent, Dimension> childComponentSizeMap = new HashMap<>();
    protected Set<ViewComponent> childComponents = new LinkedHashSet<>();

    public WrapperComponent() {
    }

    @Override
    public boolean isEmpty() {
        return this.childComponents.size() == 0;
    }

    protected void setLayout(LayoutManager layoutManager) {
        this.component.setLayout(layoutManager);
    }

    @Override
    public void addChildComponent(ViewComponent childComponent) {
        addChildComponent(childComponent, -1);
    }

    @Override
    public void addChildComponentToTheFirstPosition(ViewComponent childComponent) {
        addChildComponent(childComponent, 0);
    }

    @Override
    public void addChildComponent(ViewComponent childComponent, int position) {
        this.childComponents.add(childComponent);
        this.component.add(childComponent.getComponent(), position);
        childComponent.setParent(this);
    }

    @Override
    public void removeChildComponent(ViewComponent childComponent) {
        this.childComponents.remove(childComponent);
        this.component.remove(childComponent.getComponent());
        childComponent.setParent(null);
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
    }

    protected abstract void setNotResizableChildComponents();

    @Override
    public void resize(Dimension dimension) {
        this.component.setPreferredSize(dimension);
        loadChildComponentsSize();
        resizeChildComponents();
        setNotResizableChildComponents();
        refreshUI();
    }

    private void disposeChildComponents() {
        final Iterator<ViewComponent> childComponentIterator = getChildComponentIterator();

        while (childComponentIterator.hasNext()) {
            final ViewComponent childComponent = childComponentIterator.next();
            childComponent.dispose();
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        disposeChildComponents();
    }

    @Override
    public Iterator<ViewComponent> getChildComponentIterator() {
        return this.childComponents.iterator();
    }

    @Override
    public void refreshUI() {
        this.component.revalidate();
        this.component.repaint();
    }
}
