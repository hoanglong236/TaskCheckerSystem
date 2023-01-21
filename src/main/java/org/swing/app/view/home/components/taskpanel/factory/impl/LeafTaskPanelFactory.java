package org.swing.app.view.home.components.taskpanel.factory.impl;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.home.components.taskpanel.LeafTaskPanel;
import org.swing.app.view.home.components.taskpanel.TaskPanel;
import org.swing.app.view.home.components.taskpanel.factory.TaskPanelFactory;

public class LeafTaskPanelFactory implements TaskPanelFactory {

    @Override
    public TaskPanel createTaskPanel(HomeFrameController homeFrameController, TaskPanelDto taskPanelDto) {
        return new LeafTaskPanel(homeFrameController, taskPanelDto);
    }
}
