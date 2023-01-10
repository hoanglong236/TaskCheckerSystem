package org.swing.app.view.home.components.roottask;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.home.components.taskbase.TaskContentPanel;
import org.swing.app.view.home.components.nodetask.factory.NodeTaskPanelManagerComponentFactory;
import org.swing.app.view.taskform.roottask.factory.RootTaskFormModalFactory;

import java.awt.event.ActionListener;
import java.util.Set;

public class RootTaskContentPanel extends TaskContentPanel implements ActionListener {

    public RootTaskContentPanel(HomeFrameController homeFrameController,
            TaskPanelDto taskPanelDto, Set<TaskPanelDto> taskPanelDtos) {

        super(homeFrameController, new NodeTaskPanelManagerComponentFactory(), new RootTaskFormModalFactory(),
                taskPanelDto, taskPanelDtos);
    }
}
