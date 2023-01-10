package org.swing.app.view.home.observer;

import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.home.components.taskbase.TaskPanel;

public interface TaskPanelEventObserver {

    void handleInsertTaskPanelByDto(TaskPanelDto taskPanelDto);

    void handleUpdateTaskPanel(TaskPanel taskPanel);

    void handleDeleteTaskPanel(TaskPanel taskPanel);
}
