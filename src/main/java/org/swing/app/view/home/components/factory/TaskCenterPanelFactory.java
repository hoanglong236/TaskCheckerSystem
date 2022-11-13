package org.swing.app.view.home.components.factory;

import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.home.components.TaskCenterPanel;

public interface TaskCenterPanelFactory {

    TaskCenterPanel createTaskCenterPanel(TaskPanelDto taskPanelDto);
}
