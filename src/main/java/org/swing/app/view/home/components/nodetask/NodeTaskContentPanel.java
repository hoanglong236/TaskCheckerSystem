package org.swing.app.view.home.components.nodetask;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.home.components.leaftask.factory.LeafTaskPanelContainerWrapperFactory;
import org.swing.app.view.home.components.taskbase.TaskContentPanel;
import org.swing.app.view.taskform.nodetask.factory.NodeTaskFormModalFactory;

import java.util.Set;

public class NodeTaskContentPanel extends TaskContentPanel {

    public NodeTaskContentPanel(HomeFrameController homeFrameController,
            TaskPanelDto taskPanelDto, Set<TaskPanelDto> taskPanelDtos) {

        super(homeFrameController, new LeafTaskPanelContainerWrapperFactory(), new NodeTaskFormModalFactory(),
                taskPanelDto, taskPanelDtos);
    }
}