package org.swing.app.view.home.components.taskpanel.factory.impl;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.home.components.taskpanel.NodeTaskPanel;
import org.swing.app.view.home.components.taskpanel.TaskPanel;
import org.swing.app.view.home.components.taskpanel.factory.TaskPanelFactory;

public class NodeTaskPanelFactory implements TaskPanelFactory {

    @Override
    public TaskPanel createTaskPanel(HomeFrameController homeFrameController, TaskPanelDto taskPanelDto) {
        return new NodeTaskPanel(homeFrameController, ViewConstant.NODE_TASK_PANEL_HEIGHT, taskPanelDto);
    }
}
