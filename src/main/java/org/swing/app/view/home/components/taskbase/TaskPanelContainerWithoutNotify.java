package org.swing.app.view.home.components.taskbase;

import org.swing.app.controller.HomeFrameController;

public class TaskPanelContainerWithoutNotify extends TaskPanelContainer {

    public TaskPanelContainerWithoutNotify(HomeFrameController homeFrameController) {
        super(homeFrameController);
    }

    @Override
    protected boolean hasNotifyLabel() {
        return false;
    }
}
