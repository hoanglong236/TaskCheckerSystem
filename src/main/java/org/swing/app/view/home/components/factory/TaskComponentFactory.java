package org.swing.app.view.home.components.factory;

import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.components.ui.VerticalScrollPane;
import org.swing.app.view.home.components.TaskCenterPanel;
import org.swing.app.view.home.components.TaskPanel;
import org.swing.app.view.home.components.TaskPanelContainer;

import java.util.Set;

public interface TaskComponentFactory {

    TaskCenterPanel createTaskCenterPanel(TaskPanelDto taskPanelDto);
    TaskPanel createTaskPanel(TaskPanelDto taskPanelDto);
    VerticalScrollPane createScrollPaneToContainTaskPanels();
    TaskPanelContainer createTaskPanelContainer(String title, Set<TaskPanelDto> taskPanelDtos);
}
