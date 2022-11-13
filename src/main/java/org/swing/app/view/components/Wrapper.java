package org.swing.app.view.components;

import java.util.Iterator;

public interface Wrapper {

    boolean isEmpty();
    void addChildComponent(ViewComponentBase childComponent);
    void addChildComponentToTheFirstPosition(ViewComponentBase childComponent);
    void addChildComponent(ViewComponentBase childComponent, int position);
    void removeChildComponent(ViewComponentBase childComponent);
    Iterator<ViewComponentBase> getChildComponentIterator();
}
