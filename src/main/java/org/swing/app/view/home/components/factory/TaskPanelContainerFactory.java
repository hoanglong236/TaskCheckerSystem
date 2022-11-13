package org.swing.app.view.home.components.factory;

import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.home.components.TaskPanelContainer;

import java.util.Set;

public interface TaskPanelContainerFactory {

    TaskPanelContainer createTaskPanelContainer(String title, Set<TaskPanelDto> taskPanelDtos);
}
