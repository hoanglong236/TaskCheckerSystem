package org.swing.app.view.home.components.roottask;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.home.components.taskbase.TaskCenterPanel;


class RootTaskCenterPanel extends TaskCenterPanel {

    public RootTaskCenterPanel(HomeFrameController homeFrameController, TaskPanelDto taskPanelDto) {
        super(homeFrameController, taskPanelDto);
    }

    @Override
    protected boolean isNeedDeadlineLabel() {
        return true;
    }

    @Override
    protected boolean isNeedCompletionRateLabel() {
        return true;
    }

    @Override
    protected boolean isNeedNoteNotifyLabel() {
        return false;
    }
}
