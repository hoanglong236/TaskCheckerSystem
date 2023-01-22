package org.swing.app.view.home.observer.taskcontent;

import org.swing.app.dto.TaskDto;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.home.observer.taskcompletionrate.TaskCompletionRateEventSubject;

import java.util.LinkedHashSet;
import java.util.Set;

public abstract class TaskContentEventSubject {

    protected final Set<TaskContentEventObserver> observers = new LinkedHashSet<>();

    public void registerObserver(TaskContentEventObserver observer) {
        this.observers.add(observer);
    }

    public void removeObserver(TaskContentEventObserver observer) {
        this.observers.remove(observer);
    }

    public abstract void notifyObserversToLoadContent(TaskPanelDto masterTaskPanelDto, Set<TaskPanelDto> taskPanelDtos,
            TaskCompletionRateEventSubject masterTaskCompletionRateEventSubject);

    public abstract void notifyObserversToClearContent();

    public abstract void notifyObserversToUpdateMasterTask(TaskDto masterTaskDto);
}
