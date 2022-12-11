package org.swing.app.view.home.components.leaftask.factory;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.home.components.leaftask.LeafTaskPanelContainer;
import org.swing.app.view.home.components.taskbase.TaskPanelContainer;
import org.swing.app.view.home.components.factory.TaskPanelContainerFactory;

import java.util.Set;

public class LeafTaskPanelContainerFactory implements TaskPanelContainerFactory {

    @Override
    public TaskPanelContainer createTaskPanelContainer(HomeFrameController homeFrameController,
            String title, Set<TaskPanelDto> taskPanelDtos) {

        return new LeafTaskPanelContainer(homeFrameController, title, taskPanelDtos);
    }
}
