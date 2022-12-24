package org.swing.app.view.home.components.roottask;

import org.swing.app.controller.ControllerBase;
import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.home.components.taskbase.TaskContentPanel;
import org.swing.app.view.home.components.nodetask.factory.NodeTaskPanelManagerComponentFactory;

import java.awt.event.ActionListener;
import java.util.Set;

public class RootTaskContentPanel extends TaskContentPanel implements ActionListener {

    public RootTaskContentPanel(HomeFrameController homeFrameController,
            String title, Set<TaskPanelDto> taskPanelDtos) {

        super(homeFrameController, new NodeTaskPanelManagerComponentFactory(), title, taskPanelDtos);
    }

    @Override
    protected byte getTaskTypeToRequest() {
        return ControllerBase.ROOT_TASK_TYPE;
    }
}
