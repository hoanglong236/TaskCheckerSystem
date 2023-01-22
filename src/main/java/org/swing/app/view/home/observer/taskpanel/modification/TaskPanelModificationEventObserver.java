package org.swing.app.view.home.observer.taskpanel.modification;

import org.swing.app.dto.TaskDto;
import org.swing.app.view.home.components.taskpanel.TaskPanel;

/**
 * TODO: comment this
 */
public interface TaskPanelModificationEventObserver {

    void handleUpdateTaskInTaskPanelEvent(TaskPanel taskPanel, TaskDto newTaskDto);

    void handleDeleteTaskPanelEvent(TaskPanel taskPanel);
}
