package org.swing.app.view.home.components.leaftask.factory;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.view.home.components.factory.TaskPanelContainerFactory;
import org.swing.app.view.home.components.taskbase.TaskPanelContainer;
import org.swing.app.view.home.components.taskbase.TaskPanelContainerWithoutNotify;

public class LeafTaskPanelContainerFactory implements TaskPanelContainerFactory {

    @Override
    public TaskPanelContainer createTaskPanelContainer(HomeFrameController homeFrameController) {
        return new TaskPanelContainerWithoutNotify(homeFrameController);
    }
}
