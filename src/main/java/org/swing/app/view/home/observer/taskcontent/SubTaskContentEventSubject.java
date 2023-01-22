package org.swing.app.view.home.observer.taskcontent;

import org.swing.app.dto.TaskDto;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.home.observer.taskcompletionrate.TaskCompletionRateEventSubject;

import java.util.Set;

public class SubTaskContentEventSubject extends TaskContentEventSubject {

    @Override
    public void notifyObserversToLoadContent(TaskPanelDto masterTaskPanelDto, Set<TaskPanelDto> taskPanelDtos,
            TaskCompletionRateEventSubject masterTaskCompletionRateEventSubject) {

        for (final TaskContentEventObserver observer : this.observers) {
            observer.handleLoadSubContent(masterTaskPanelDto, taskPanelDtos,
                    masterTaskCompletionRateEventSubject);
        }
    }

    public void notifyObserversToClearContent() {
        for (final TaskContentEventObserver observer : this.observers) {
            observer.handleClearSubContent();
        }
    }

    public void notifyObserversToUpdateMasterTask(TaskDto masterTaskDto) {
        for (final TaskContentEventObserver observer : this.observers) {
            observer.handleUpdateMasterTaskInSubContent(masterTaskDto);
        }
    }
}
