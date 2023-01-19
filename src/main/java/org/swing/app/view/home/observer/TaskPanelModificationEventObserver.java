package org.swing.app.view.home.observer;

import org.swing.app.dto.TaskDto;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.home.components.taskpanel.TaskPanel;

/**
 * TODO: comment this
 */
public interface TaskPanelModificationEventObserver {

    void handleUpdateTaskPanel(TaskPanel taskPanel, TaskPanelDto updatedTaskPanelDto);
    void handleUpdateTaskInTaskPanel(TaskPanel taskPanel, TaskDto updatedTaskDto);
    void handleUpdateTaskCompletionRateInTaskPanel(TaskPanel taskPanel,
            int completedChildTaskCount, int childTaskCount);

    void handleDeleteTaskPanel(TaskPanel taskPanel);
}
