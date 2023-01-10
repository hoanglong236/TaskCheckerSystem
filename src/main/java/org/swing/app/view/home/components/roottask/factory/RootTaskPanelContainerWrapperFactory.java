package org.swing.app.view.home.components.roottask.factory;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.home.components.factory.TaskPanelContainerWrapperFactory;
import org.swing.app.view.home.components.roottask.RootTaskPanelContainerWrapper;
import org.swing.app.view.home.components.taskbase.TaskPanelContainerWrapper;

import java.util.Set;

public class RootTaskPanelContainerWrapperFactory implements TaskPanelContainerWrapperFactory {

    @Override
    public TaskPanelContainerWrapper createTaskPanelContainerWrapper(HomeFrameController homeFrameController,
            String title, Set<TaskPanelDto> taskPanelDtos) {

        return new RootTaskPanelContainerWrapper(homeFrameController, title, taskPanelDtos);
    }
}
