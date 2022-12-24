package org.swing.app.view.components.ui;

import org.swing.app.view.components.SimpleComponent;
import org.swing.app.view.components.ViewComponent;
import org.swing.app.view.components.factory.JComponentFactory;

import javax.swing.JPopupMenu;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class Popup extends SimpleComponent {

    protected final Set<PopupItem> popupItems = new LinkedHashSet<>();

    public Popup() {
        this.component = JComponentFactory.createJPopupMenu();
    }

    public JPopupMenu getPopupMenu() {
        return (JPopupMenu) this.component;
    }

    public void addPopupItem(PopupItem popupItem) {
        this.popupItems.add(popupItem);
        this.component.add(popupItem.getComponent());
        pack();
    }

    public void removeItem(PopupItem popupItem) {
        this.popupItems.remove(popupItem);
        this.component.remove(popupItem.getComponent());
        pack();
    }

    public Iterator<PopupItem> getPopupItemIterator() {
        return this.popupItems.iterator();
    }

    public void show(ViewComponent parent, int x, int y) {
        ((JPopupMenu) this.component).show(parent.getComponent(), x, y);
    }

    public void dispose() {
        disposePopupItems();
    }

    private void disposePopupItems() {
        final Iterator<PopupItem> popupItemIterator = getPopupItemIterator();

        while (popupItemIterator.hasNext()) {
            final PopupItem popupItem = popupItemIterator.next();
            popupItem.dispose();
        }
    }

    private void pack() {
        ((JPopupMenu) this.component).pack();
    }
}
