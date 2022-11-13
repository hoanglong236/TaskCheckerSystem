package org.swing.app.view.components;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public abstract class WrapperComponent extends ViewComponentBase implements Wrapper {

    protected Map<ViewComponentBase, Dimension> childComponentSizeMap = new HashMap<>();
    protected Set<ViewComponentBase> childComponents = new LinkedHashSet<>();

    public WrapperComponent() {
        this.component = new JPanel();
    }

    @Override
    public boolean isEmpty() {
        return this.childComponents.size() == 0;
    }

    @Override
    public void addChildComponent(ViewComponentBase childComponent) {
        addChildComponent(childComponent, -1);
    }

    @Override
    public void addChildComponentToTheFirstPosition(ViewComponentBase childComponent) {
        addChildComponent(childComponent, 0);
    }

    @Override
    public void addChildComponent(ViewComponentBase childComponent, int position) {
        this.childComponents.add(childComponent);
        this.component.add(childComponent.getComponent(), position);
    }

    @Override
    public void removeChildComponent(ViewComponentBase childComponent) {
        this.childComponents.remove(childComponent);
        this.component.remove(childComponent.getComponent());
    }

    protected abstract void loadChildComponentsSize();

    private void resizeChildComponents() {
        final Iterator<ViewComponentBase> childComponentIterator = getChildComponentIterator();

        while (childComponentIterator.hasNext()) {
            final ViewComponentBase childComponent = childComponentIterator.next();

            if (childComponent.isResizable()) {
                childComponent.resize(this.childComponentSizeMap.get(childComponent));
            }
        }
    }

    @Override
    public void resize(Dimension dimension) {
        this.component.setPreferredSize(dimension);
        loadChildComponentsSize();
        resizeChildComponents();
    }

    private void disposeChildComponents() {
        final Iterator<ViewComponentBase> childComponentIterator = getChildComponentIterator();

        while (childComponentIterator.hasNext()) {
            final ViewComponentBase childComponent = childComponentIterator.next();
            childComponent.dispose();
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        disposeChildComponents();
    }

    @Override
    public Iterator<ViewComponentBase> getChildComponentIterator() {
        return this.childComponents.iterator();
    }
}
