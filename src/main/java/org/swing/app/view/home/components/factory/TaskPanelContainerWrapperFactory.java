package org.swing.app.view.home.components.factory;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.home.components.taskbase.TaskPanelContainerWrapper;

import java.util.Set;

public interface TaskPanelContainerWrapperFactory {

    TaskPanelContainerWrapper createTaskPanelContainerWrapper(HomeFrameController homeFrameController,
            String title, Set<TaskPanelDto> taskPanelDtos);
}
