package org.swing.app.view.home.components.nodetask.factory;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.view.home.components.factory.TaskPanelContainerFactory;
import org.swing.app.view.home.components.taskbase.TaskPanelContainer;
import org.swing.app.view.home.components.taskbase.TaskPanelContainerWithNotify;
import org.swing.app.view.home.components.taskbase.TaskPanelManager;

public class NodeTaskPanelContainerFactory implements TaskPanelContainerFactory {

    @Override
    public TaskPanelContainer createTaskPanelContainer(
            HomeFrameController homeFrameController, TaskPanelManager taskPanelManager) {

        return new TaskPanelContainerWithNotify(homeFrameController, taskPanelManager);
    }
}
