package org.swing.app.view.home.observer;

public interface TaskPanelEventObservable {

    void registerObserver(TaskPanelEventObserver observer);
    void removeObserver(TaskPanelEventObserver observer);

    void notifyObserversWhenInsertTaskPanel();
    void notifyObserversWhenUpdateTaskPanel();
    void notifyObserversWhenDeleteTaskPanel();
}
