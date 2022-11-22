package org.swing.app.view.components;

import java.util.Iterator;

public interface Wrapper {

    boolean isEmpty();
    void addChildComponent(ViewComponent childComponent);
    void addChildComponentToTheFirstPosition(ViewComponent childComponent);
    void addChildComponent(ViewComponent childComponent, int position);
    void removeChildComponent(ViewComponent childComponent);
    Iterator<ViewComponent> getChildComponentIterator();
}
