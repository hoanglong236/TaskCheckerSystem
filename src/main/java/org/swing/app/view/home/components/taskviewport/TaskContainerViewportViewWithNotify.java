package org.swing.app.view.home.components.taskviewport;

import org.swing.app.controller.HomeFrameController;

public class TaskContainerViewportViewWithNotify extends TaskContainerViewportView {

    public TaskContainerViewportViewWithNotify(HomeFrameController homeFrameController) {
        super(homeFrameController);
    }

    @Override
    protected boolean hasNotifyLabel() {
        return true;
    }
}
