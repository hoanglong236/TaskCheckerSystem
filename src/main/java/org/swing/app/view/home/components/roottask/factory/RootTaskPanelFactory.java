package org.swing.app.view.home.components.roottask.factory;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.home.components.roottask.RootTaskPanel;
import org.swing.app.view.home.components.taskbase.TaskPanel;
import org.swing.app.view.home.components.factory.TaskPanelFactory;
import org.swing.app.view.home.components.taskbase.TaskPanelManagerComponent;

public class RootTaskPanelFactory implements TaskPanelFactory {

    @Override
    public TaskPanel createTaskPanel(HomeFrameController homeFrameController,
            TaskPanelManagerComponent taskPanelManager, TaskPanelDto taskPanelDto) {

        return new RootTaskPanel(homeFrameController, taskPanelManager,
                ViewConstant.ROOT_TASK_PANEL_HEIGHT, taskPanelDto);
    }
}
