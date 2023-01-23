package org.swing.app.view.components;

import java.util.Iterator;

public interface Wrapper {

    /**
     * Appends the specified component to the end of this container.
     * @param childComponent the component to be added
     * @throws NullPointerException if childComponent is null
     */
    void addChildComponent(ViewComponent childComponent);

    /**
     * Adds the specified component to this container at the given position.
     * @param childComponent the component to be added
     * @throws NullPointerException if childComponent is null
     */
    void addChildComponent(ViewComponent childComponent, int position);

    void removeChildComponent(ViewComponent childComponent);

    /**
     * Get position of childComponent in this container
     * @param childComponent
     * @return position of childComponent
     */
    int getChildComponentPosition(ViewComponent childComponent);

    int getChildComponentCount();

    Iterator<ViewComponent> getChildComponentIterator();

    /**
     * After adding, removing or resizing component, you should call this method.
     * Basically, it's equivalent to first calling the revalidate() method,
     * and then calling the repaint() method
     */
    void refreshUI();
}
