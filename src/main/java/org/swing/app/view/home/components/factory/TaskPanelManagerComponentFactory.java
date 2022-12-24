package org.swing.app.view.home.components.factory;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.home.components.taskbase.TaskPanelManagerComponent;

import java.util.Set;

public interface TaskPanelManagerComponentFactory {

    TaskPanelManagerComponent createTaskPanelManagerComponent(HomeFrameController homeFrameController,
            String title, Set<TaskPanelDto> taskPanelDtos);
}
