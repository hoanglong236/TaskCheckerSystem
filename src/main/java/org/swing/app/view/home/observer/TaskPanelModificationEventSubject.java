package org.swing.app.view.home.observer;

/**
 * TODO: comment this
 */
public interface TaskPanelModificationEventSubject {

    void registerObserver(TaskPanelModificationEventObserver observer);
    void removeObserver(TaskPanelModificationEventObserver observer);
}
