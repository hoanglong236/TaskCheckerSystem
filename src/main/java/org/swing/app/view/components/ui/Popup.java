package org.swing.app.view.components.ui;

import javax.swing.JPopupMenu;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class Popup {

    protected final JPopupMenu popupMenu;
    protected final Set<PopupItem> popupItems = new LinkedHashSet<>();

    public Popup() {
        this.popupMenu = new JPopupMenu();
    }

    public JPopupMenu getPopupMenu() {
        return this.popupMenu;
    }

    public void addPopupItemByName(String itemName) {
        final PopupItem popupItem = new PopupItem(itemName);
        addPopupItem(popupItem);
    }

    public void addPopupItem(PopupItem popupItem) {
        this.popupItems.add(popupItem);
        this.popupMenu.add(popupItem.getMenuItem());
    }

    public void removeItem(PopupItem popupItem) {
        this.popupItems.remove(popupItem);
        this.popupMenu.remove(popupItem.getMenuItem());
        this.popupMenu.pack();
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
}
