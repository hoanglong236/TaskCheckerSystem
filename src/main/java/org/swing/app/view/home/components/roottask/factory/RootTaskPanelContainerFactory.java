package org.swing.app.view.home.components.roottask.factory;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.view.home.components.factory.TaskPanelContainerFactory;
import org.swing.app.view.home.components.taskbase.TaskPanelContainer;
import org.swing.app.view.home.components.taskbase.TaskPanelContainerWithoutNotify;
import org.swing.app.view.home.components.taskbase.TaskPanelManager;

public class RootTaskPanelContainerFactory implements TaskPanelContainerFactory {

    @Override
    public TaskPanelContainer createTaskPanelContainer(HomeFrameController homeFrameController,
            TaskPanelManager taskPanelManager) {

        return new TaskPanelContainerWithoutNotify(homeFrameController, taskPanelManager);
    }
}
