package org.swing.app.view.components.ui;

import org.swing.app.view.components.SimpleComponent;
import org.swing.app.view.components.ViewComponent;
import org.swing.app.view.components.factory.JComponentFactory;
import org.swing.app.view.components.ui.button.PopupItem;

import javax.swing.JPopupMenu;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class Popup extends SimpleComponent {

    protected final Set<PopupItem> popupItems = new LinkedHashSet<>();

    public Popup() {
        this.sourceComponent = JComponentFactory.createJPopupMenu();
    }

    public JPopupMenu getPopupMenu() {
        return (JPopupMenu) this.sourceComponent;
    }

    public void addPopupItem(PopupItem popupItem) {
        this.popupItems.add(popupItem);
        this.sourceComponent.add(popupItem.getSourceComponent());
        pack();
    }

    public void removeItem(PopupItem popupItem) {
        this.popupItems.remove(popupItem);
        this.sourceComponent.remove(popupItem.getSourceComponent());
        pack();
    }

    public Iterator<PopupItem> getPopupItemIterator() {
        return this.popupItems.iterator();
    }

    public void show(ViewComponent parent, int x, int y) {
        ((JPopupMenu) this.sourceComponent).show(parent.getSourceComponent(), x, y);
    }

    @Override
    public void cancelAllEventListeners() {
        cancelAllEventListenersForPopupItems();
    }

    private void cancelAllEventListenersForPopupItems() {
        final Iterator<PopupItem> popupItemIterator = getPopupItemIterator();

        while (popupItemIterator.hasNext()) {
            final PopupItem popupItem = popupItemIterator.next();
            popupItem.cancelAllEventListeners();
        }
    }

    private void pack() {
        ((JPopupMenu) this.sourceComponent).pack();
    }
}
