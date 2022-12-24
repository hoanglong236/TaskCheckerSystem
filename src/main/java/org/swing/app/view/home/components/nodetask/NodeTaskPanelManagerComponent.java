package org.swing.app.view.home.components.nodetask;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.home.components.nodetask.factory.NodeTaskPanelContainerFactory;
import org.swing.app.view.home.components.nodetask.factory.NodeTaskPanelFactory;
import org.swing.app.view.home.components.taskbase.TaskPanelManagerComponent;

import java.util.Set;

public class NodeTaskPanelManagerComponent extends TaskPanelManagerComponent {

    public NodeTaskPanelManagerComponent(HomeFrameController homeFrameController,
            String title, Set<TaskPanelDto> taskPanelDtos) {

        super(homeFrameController, new NodeTaskPanelFactory(), new NodeTaskPanelContainerFactory(),
                title, taskPanelDtos);
    }
}
