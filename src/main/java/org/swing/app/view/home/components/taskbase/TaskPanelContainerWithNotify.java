package org.swing.app.view.home.components.taskbase;

import org.swing.app.controller.HomeFrameController;

public class TaskPanelContainerWithNotify extends TaskPanelContainer {

    public TaskPanelContainerWithNotify(HomeFrameController homeFrameController, TaskPanelManager taskPanelManager) {
        super(homeFrameController, taskPanelManager);
    }

    @Override
    protected boolean hasNotifyLabel() {
        return true;
    }
}
