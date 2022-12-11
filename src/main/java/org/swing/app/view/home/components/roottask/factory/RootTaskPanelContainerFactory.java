package org.swing.app.view.home.components.roottask.factory;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.home.components.roottask.RootTaskPanelContainer;
import org.swing.app.view.home.components.taskbase.TaskPanelContainer;
import org.swing.app.view.home.components.factory.TaskPanelContainerFactory;

import java.util.Set;

public class RootTaskPanelContainerFactory implements TaskPanelContainerFactory {

    @Override
    public TaskPanelContainer createTaskPanelContainer(HomeFrameController homeFrameController,
            String title, Set<TaskPanelDto> taskPanelDtos) {

        return new RootTaskPanelContainer(homeFrameController, title, taskPanelDtos);
    }
}
