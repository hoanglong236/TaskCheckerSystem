package org.swing.app.view.home.components.factory;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.home.components.taskbase.TaskPanelContainer;

import java.util.Set;

public interface TaskPanelContainerFactory {

    TaskPanelContainer createTaskPanelContainer(HomeFrameController homeFrameController,
            String title, Set<TaskPanelDto> taskPanelDtos);
}
