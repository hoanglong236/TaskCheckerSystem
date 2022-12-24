package org.swing.app.view.home.components.factory;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.view.home.components.taskbase.TaskPanelContainer;

public interface TaskPanelContainerFactory {

    TaskPanelContainer createTaskPanelContainer(HomeFrameController homeFrameController);
}
