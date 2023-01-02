package org.swing.app.view.home.components.leaftask.factory;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.view.home.components.factory.TaskPanelContainerFactory;
import org.swing.app.view.home.components.taskbase.TaskPanelContainer;
import org.swing.app.view.home.components.taskbase.TaskPanelContainerWithoutNotify;
import org.swing.app.view.home.components.taskbase.TaskPanelManager;

public class LeafTaskPanelContainerFactory implements TaskPanelContainerFactory {

    @Override
    public TaskPanelContainer createTaskPanelContainer(HomeFrameController homeFrameController,
            TaskPanelManager taskPanelManager) {

        return new TaskPanelContainerWithoutNotify(homeFrameController, taskPanelManager);
    }
}
