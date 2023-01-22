package org.swing.app.view.home.observer.taskcontent;

import org.swing.app.dto.TaskDto;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.home.observer.taskcompletionrate.TaskCompletionRateEventSubject;

import java.util.Set;

public interface TaskContentEventObserver {

    void handleLoadMainContent(TaskPanelDto masterTaskPanelDto, Set<TaskPanelDto> taskPanelDtos,
            TaskCompletionRateEventSubject masterTaskCompletionRateEventSubject);

    void handleUpdateMasterTaskInMainContent(TaskDto masterTaskDto);

    void handleClearMainContent();

    void handleLoadSubContent(TaskPanelDto masterTaskPanelDto, Set<TaskPanelDto> taskPanelDtos,
            TaskCompletionRateEventSubject masterTaskCompletionRateEventSubject);

    void handleUpdateMasterTaskInSubContent(TaskDto masterTaskDto);

    void handleClearSubContent();
}
