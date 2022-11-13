package org.swing.app.view.home.components.roottask;

import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.home.components.TaskPanelContainer;
import org.swing.app.view.home.components.roottask.factory.RootTaskPanelContainerChildComponentFactory;

import java.util.Set;

public class RootTaskPanelContainer extends TaskPanelContainer {

    public RootTaskPanelContainer(String title, Set<TaskPanelDto> taskPanelDtos) {
        super(new RootTaskPanelContainerChildComponentFactory(), title, taskPanelDtos);
    }
}
