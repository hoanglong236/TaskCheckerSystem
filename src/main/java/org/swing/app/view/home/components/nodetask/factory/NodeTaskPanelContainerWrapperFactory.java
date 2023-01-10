package org.swing.app.view.home.components.nodetask.factory;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.home.components.factory.TaskPanelContainerWrapperFactory;
import org.swing.app.view.home.components.nodetask.NodeTaskPanelContainerWrapper;
import org.swing.app.view.home.components.taskbase.TaskPanelContainerWrapper;

import java.util.Set;

public class NodeTaskPanelContainerWrapperFactory implements TaskPanelContainerWrapperFactory {

    @Override
    public TaskPanelContainerWrapper createTaskPanelContainerWrapper(HomeFrameController homeFrameController,
            String title, Set<TaskPanelDto> taskPanelDtos) {

        return new NodeTaskPanelContainerWrapper(homeFrameController, title, taskPanelDtos);
    }
}
