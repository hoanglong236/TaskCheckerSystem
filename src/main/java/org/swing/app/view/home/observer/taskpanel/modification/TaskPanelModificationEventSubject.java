package org.swing.app.view.home.observer.taskpanel.modification;

import org.swing.app.dto.TaskDto;
import org.swing.app.view.home.components.taskpanel.TaskPanel;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * TODO: comment this
 */
public class TaskPanelModificationEventSubject {

    private final Set<TaskPanelModificationEventObserver> observers = new LinkedHashSet<>();

    private final TaskPanel taskPanel;

    public TaskPanelModificationEventSubject(TaskPanel taskPanel) {
        this.taskPanel = taskPanel;
    }

    public void registerObserver(TaskPanelModificationEventObserver observer) {
        this.observers.add(observer);
    }

    public void removeObserver(TaskPanelModificationEventObserver observer) {
        this.observers.remove(observer);
    }

    public void notifyObserversForUpdateTaskEvent(TaskDto newTaskDto) {
        for (final TaskPanelModificationEventObserver observer : this.observers) {
            observer.handleUpdateTaskInTaskPanelEvent(this.taskPanel, newTaskDto);
        }
    }

    public void notifyObserversForDeleteEvent() {
        for (final TaskPanelModificationEventObserver observer : this.observers) {
            observer.handleDeleteTaskPanelEvent(this.taskPanel);
        }
    }
}
