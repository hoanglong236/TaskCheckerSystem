package org.swing.app.view.home.components.taskcontentpanel;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.home.components.taskpanel.factory.impl.LeafTaskPanelFactory;
import org.swing.app.view.taskform.taskformmodal.factory.impl.LeafTaskFormModalFactory;

import java.util.Set;

public class NodeTaskContentPanel extends TaskContentPanel {

    public NodeTaskContentPanel(HomeFrameController homeFrameController,
            TaskPanelDto taskPanelDto, Set<TaskPanelDto> taskPanelDtos) {

        super(homeFrameController, new LeafTaskFormModalFactory(), new LeafTaskPanelFactory(),
                taskPanelDto, taskPanelDtos);
    }
}