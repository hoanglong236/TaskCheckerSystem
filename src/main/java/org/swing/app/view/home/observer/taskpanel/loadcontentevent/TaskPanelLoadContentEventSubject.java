package org.swing.app.view.home.observer.taskpanel.loadcontentevent;

import org.swing.app.dto.TaskDto;

import java.util.LinkedHashSet;
import java.util.Set;

public class TaskPanelLoadContentEventSubject {

    private final Set<TaskPanelLoadContentEventObserver> observers = new LinkedHashSet<>();

    public TaskPanelLoadContentEventSubject() {
    }

    public void registerObserver(TaskPanelLoadContentEventObserver observer) {
        this.observers.add(observer);
    }

    public void removeObserver(TaskPanelLoadContentEventObserver observer) {
        this.observers.remove(observer);
    }

    public void notifyObserversToUpdateMasterTask(TaskDto masterTaskDto) {

    }
}
