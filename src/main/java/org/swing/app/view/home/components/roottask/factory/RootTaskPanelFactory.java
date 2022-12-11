package org.swing.app.view.home.components.roottask.factory;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.home.components.roottask.RootTaskPanel;
import org.swing.app.view.home.components.taskbase.TaskPanel;
import org.swing.app.view.home.components.factory.TaskPanelFactory;

public class RootTaskPanelFactory implements TaskPanelFactory {

    @Override
    public TaskPanel createTaskPanel(HomeFrameController homeFrameController, TaskPanelDto taskPanelDto) {
        return new RootTaskPanel(homeFrameController, ViewConstant.ROOT_TASK_PANEL_HEIGHT, taskPanelDto);
    }
}
