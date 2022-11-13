package org.swing.app.view.home.components.nodetask;

import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.home.components.TaskPanelContainer;
import org.swing.app.view.home.components.nodetask.factory.NodeTaskPanelContainerChildComponentFactory;

import java.util.Set;

public class NodeTaskPanelContainer extends TaskPanelContainer {

    public NodeTaskPanelContainer(String title, Set<TaskPanelDto> taskPanelDtos) {
        super(new NodeTaskPanelContainerChildComponentFactory(), title, taskPanelDtos);
    }
}
