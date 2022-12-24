package org.swing.app.view.home.components.nodetask.factory;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.home.components.nodetask.NodeTaskPanel;
import org.swing.app.view.home.components.taskbase.TaskPanel;
import org.swing.app.view.home.components.factory.TaskPanelFactory;
import org.swing.app.view.home.components.taskbase.TaskPanelManagerComponent;

public class NodeTaskPanelFactory implements TaskPanelFactory {

    @Override
    public TaskPanel createTaskPanel(HomeFrameController homeFrameController,
            TaskPanelManagerComponent taskPanelManager, TaskPanelDto taskPanelDto) {

        return new NodeTaskPanel(homeFrameController, taskPanelManager,
                ViewConstant.NODE_TASK_PANEL_HEIGHT, taskPanelDto);
    }
}
