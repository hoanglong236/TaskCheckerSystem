package org.swing.app.view.home.components.roottask.factory;

import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.home.components.TaskPanelContainer;
import org.swing.app.view.home.components.factory.TaskPanelContainerFactory;

import java.util.Set;

public class RootTaskPanelContainerFactory implements TaskPanelContainerFactory {

    @Override
    public TaskPanelContainer createTaskPanelContainer(String title, Set<TaskPanelDto> taskPanelDtos) {
        return new RootTaskPanelContainer(title, taskPanelDtos);
    }
}

class RootTaskPanelContainer extends TaskPanelContainer {

    public RootTaskPanelContainer(String title, Set<TaskPanelDto> taskPanelDtos) {
        super(new RootTaskPanelContainerChildComponentFactory(), title, taskPanelDtos);
    }
}