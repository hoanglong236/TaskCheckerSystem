package org.swing.app.view.home.components.taskpanel.factory;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.home.components.taskpanel.TaskPanel;

public interface TaskPanelFactory {

    TaskPanel createTaskPanel(HomeFrameController homeFrameController, TaskPanelDto taskPanelDto);
}
