package org.swing.app.view.home.components.nodetask.factory;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.home.components.nodetask.NodeTaskPanelContainer;
import org.swing.app.view.home.components.taskbase.TaskPanelContainer;
import org.swing.app.view.home.components.factory.TaskPanelContainerFactory;

import java.util.Set;

public class NodeTaskPanelContainerFactory implements TaskPanelContainerFactory {

    @Override
    public TaskPanelContainer createTaskPanelContainer(HomeFrameController homeFrameController,
            String title, Set<TaskPanelDto> taskPanelDtos) {

        return new NodeTaskPanelContainer(homeFrameController, title, taskPanelDtos);
    }
}
