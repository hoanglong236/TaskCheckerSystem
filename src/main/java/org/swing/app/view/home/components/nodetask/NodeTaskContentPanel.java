package org.swing.app.view.home.components.nodetask;

import org.swing.app.controller.ControllerBase;
import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.home.components.leaftask.factory.LeafTaskPanelManagerComponentFactory;
import org.swing.app.view.home.components.taskbase.TaskContentPanel;

import java.util.Set;

public class NodeTaskContentPanel extends TaskContentPanel {

    public NodeTaskContentPanel(HomeFrameController homeFrameController,
            String title, Set<TaskPanelDto> taskPanelDtos) {

        super(homeFrameController, new LeafTaskPanelManagerComponentFactory(), title, taskPanelDtos);
    }

    @Override
    protected byte getTaskTypeToRequest() {
        return ControllerBase.NODE_TASK_TYPE;
    }
}