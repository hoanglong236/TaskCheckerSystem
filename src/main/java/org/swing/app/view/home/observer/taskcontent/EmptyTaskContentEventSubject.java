package org.swing.app.view.home.observer.taskcontent;

import org.swing.app.dto.TaskDto;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.home.observer.taskcompletionrate.TaskCompletionRateEventSubject;

import java.util.Set;

public class EmptyTaskContentEventSubject extends TaskContentEventSubject {

    @Override
    public void registerObserver(TaskContentEventObserver observer) {}

    @Override
    public void removeObserver(TaskContentEventObserver observer) {}

    @Override
    public void notifyObserversToLoadContent(TaskPanelDto masterTaskPanelDto, Set<TaskPanelDto> taskPanelDtos,
            TaskCompletionRateEventSubject masterTaskCompletionRateEventSubject) {}

    @Override
    public void notifyObserversToClearContent() {}

    @Override
    public void notifyObserversToUpdateMasterTask(TaskDto masterTaskDto) {}
}
