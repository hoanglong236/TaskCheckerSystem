package org.swing.app.view.home.components.nodetask.factory;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.home.components.nodetask.NodeTaskPanel;
import org.swing.app.view.home.components.taskbase.TaskPanel;
import org.swing.app.view.home.components.factory.TaskPanelFactory;

public class NodeTaskPanelFactory implements TaskPanelFactory {

    @Override
    public TaskPanel createTaskPanel(HomeFrameController homeFrameController, TaskPanelDto taskPanelDto) {
        return new NodeTaskPanel(homeFrameController, ViewConstant.NODE_TASK_PANEL_HEIGHT, taskPanelDto);
    }
}
