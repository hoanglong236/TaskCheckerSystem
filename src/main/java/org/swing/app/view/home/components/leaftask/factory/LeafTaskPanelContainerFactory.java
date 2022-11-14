package org.swing.app.view.home.components.leaftask.factory;

import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.home.components.TaskPanelContainer;
import org.swing.app.view.home.components.factory.TaskPanelContainerFactory;

import java.util.Set;

public class LeafTaskPanelContainerFactory implements TaskPanelContainerFactory {

    @Override
    public TaskPanelContainer createTaskPanelContainer(String title, Set<TaskPanelDto> taskPanelDtos) {
        return new LeafTaskPanelContainer(title, taskPanelDtos);
    }
}

class LeafTaskPanelContainer extends TaskPanelContainer {

    public LeafTaskPanelContainer(String title, Set<TaskPanelDto> taskPanelDtos) {
        super(new LeafTaskPanelContainerChildComponentFactory(), title, taskPanelDtos);
    }
}
