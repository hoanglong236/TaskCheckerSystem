package org.swing.app.view.home.observer.taskpanel.modificationevent;

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

    public void notifyObserversToUpdateTask(TaskDto updatedTaskDto) {
        for (final TaskPanelModificationEventObserver observer : this.observers) {
            observer.handleUpdateTaskInTaskPanel(this.taskPanel, updatedTaskDto);
        }
    }

    public void notifyObserversToUpdateTaskCompletionRate(int completedChildTaskCount, int childTaskCount) {
        for (final TaskPanelModificationEventObserver observer : this.observers) {
            observer.handleUpdateTaskCompletionRateInTaskPanel(this.taskPanel,
                    completedChildTaskCount, childTaskCount);
        }
    }

    public void notifyObserversToDelete() {
        for (final TaskPanelModificationEventObserver observer : this.observers) {
            observer.handleDeleteTaskPanel(this.taskPanel);
        }
    }
}
