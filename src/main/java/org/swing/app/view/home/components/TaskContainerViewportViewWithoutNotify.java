package org.swing.app.view.home.components;

import org.swing.app.controller.HomeFrameController;

public class TaskContainerViewportViewWithoutNotify extends TaskContainerViewportView {

    public TaskContainerViewportViewWithoutNotify(HomeFrameController homeFrameController) {
        super(homeFrameController);
    }

    @Override
    protected boolean hasNotifyLabel() {
        return false;
    }
}
