package org.swing.app.view.home.observer;

public interface TaskPanelDeleteEventSubject extends TaskPanelModificationEventSubject {

    void notifyObserversToDelete();
}
