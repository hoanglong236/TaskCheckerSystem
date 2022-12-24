package org.swing.app.view.components;

import java.util.Iterator;

public interface Wrapper {

    /**
     * add child component to the last position in the UI
     * @param childComponent
     */
    void addChildComponent(ViewComponent childComponent);
    void addChildComponent(ViewComponent childComponent, int position);
    void removeChildComponent(ViewComponent childComponent);
    int getChildComponentPosition(ViewComponent childComponent);
    int getChildComponentCount();
    Iterator<ViewComponent> getChildComponentIterator();

    void refreshUI();
}
