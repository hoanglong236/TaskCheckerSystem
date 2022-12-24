package org.swing.app.view.home.components.factory;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.home.components.taskbase.TaskPanel;
import org.swing.app.view.home.components.taskbase.TaskPanelManagerComponent;

public interface TaskPanelFactory {

    TaskPanel createTaskPanel(HomeFrameController homeFrameController,
            TaskPanelManagerComponent taskPanelManager, TaskPanelDto taskPanelDto);
}
