package org.swing.app.view.home.components.factory;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.home.components.taskbase.TaskPanel;

public interface TaskPanelFactory {

    TaskPanel createTaskPanel(HomeFrameController homeFrameController, TaskPanelDto taskPanelDto);
}
