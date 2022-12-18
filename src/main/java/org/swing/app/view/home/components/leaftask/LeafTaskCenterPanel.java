package org.swing.app.view.home.components.leaftask;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.home.components.taskbase.TaskCenterPanel;

class LeafTaskCenterPanel extends TaskCenterPanel {

    public LeafTaskCenterPanel(HomeFrameController homeFrameController, TaskPanelDto taskPanelDto) {
        super(homeFrameController, taskPanelDto);
    }

    @Override
    protected boolean isNeedDeadlineLabel() {
        return false;
    }

    @Override
    protected boolean isNeedCompletionRateLabel() {
        return false;
    }

    @Override
    protected boolean isNeedNoteNotifyLabel() {
        return false;
    }
}
