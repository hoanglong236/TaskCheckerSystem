package org.swing.app.view.home.components.leaftask.factory;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.home.components.factory.TaskPanelContainerWrapperFactory;
import org.swing.app.view.home.components.leaftask.LeafTaskPanelContainerWrapper;
import org.swing.app.view.home.components.taskbase.TaskPanelContainerWrapper;

import java.util.Set;

public class LeafTaskPanelContainerWrapperFactory implements TaskPanelContainerWrapperFactory {

    @Override
    public TaskPanelContainerWrapper createTaskPanelContainerWrapper(HomeFrameController homeFrameController,
            String title, Set<TaskPanelDto> taskPanelDtos) {

        return new LeafTaskPanelContainerWrapper(homeFrameController, title, taskPanelDtos);
    }
}
