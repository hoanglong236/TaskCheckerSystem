package org.swing.app.view.home.components.taskcontentpanel;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.home.components.taskpanel.factory.impl.NodeTaskPanelFactory;
import org.swing.app.view.taskform.taskformmodal.factory.impl.NodeTaskFormModalFactory;

import java.util.Set;

public class RootTaskContentPanel extends TaskContentPanel {

    public RootTaskContentPanel(HomeFrameController homeFrameController,
            TaskPanelDto masterTaskPanelDto, Set<TaskPanelDto> taskPanelDtos) {

        super(homeFrameController, new NodeTaskFormModalFactory(), new NodeTaskPanelFactory(),
                masterTaskPanelDto, taskPanelDtos);
    }
}
