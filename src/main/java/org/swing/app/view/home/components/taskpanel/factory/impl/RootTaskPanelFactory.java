package org.swing.app.view.home.components.taskpanel.factory.impl;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.home.components.taskpanel.RootTaskPanel;
import org.swing.app.view.home.components.taskpanel.TaskPanel;
import org.swing.app.view.home.components.taskpanel.factory.TaskPanelFactory;

public class RootTaskPanelFactory implements TaskPanelFactory {

    @Override
    public TaskPanel createTaskPanel(HomeFrameController homeFrameController, TaskPanelDto taskPanelDto) {
        return new RootTaskPanel(homeFrameController, ViewConstant.ROOT_TASK_PANEL_HEIGHT, taskPanelDto);
    }
}
