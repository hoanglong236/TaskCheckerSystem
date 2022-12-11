package org.swing.app.view.components.ui;

import org.swing.app.view.components.SimpleComponent;
import org.swing.app.view.components.factory.JComponentFactory;
import org.swing.app.view.components.factory.UIComponentFactory;

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

    public void addPopupItemByName(String itemName) {
        final PopupItem popupItem = UIComponentFactory.createPopupItem(itemName);
        addPopupItem(popupItem);
    }

    public void addPopupItem(PopupItem popupItem) {
        this.popupItems.add(popupItem);
        this.component.add(popupItem.getMenuItem());
        pack();
    }

    public void removeItem(PopupItem popupItem) {
        this.popupItems.remove(popupItem);
        this.component.remove(popupItem.getMenuItem());
        pack();
    }

    public Iterator<PopupItem> getPopupItemIterator() {
        return this.popupItems.iterator();
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
