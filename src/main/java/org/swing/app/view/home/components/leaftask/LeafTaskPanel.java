package org.swing.app.view.home.components.leaftask;

import org.swing.app.controller.ControllerBase;
import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.home.components.taskbase.TaskPanel;
import org.swing.app.view.home.components.taskbase.TaskPanelManagerComponent;

public class LeafTaskPanel extends TaskPanel {

    public LeafTaskPanel(HomeFrameController homeFrameController, TaskPanelManagerComponent taskPanelManager,
            int preferHeight, TaskPanelDto taskPanelDto) {

        super(homeFrameController, taskPanelManager, preferHeight, taskPanelDto);
    }

    @Override
    protected boolean isNeedStatusChecker() {
        return true;
    }

    @Override
    protected boolean isNeedImportantLabel() {
        return false;
    }

    @Override
    protected void initTaskCenterPanel(TaskPanelDto taskPanelDto) {
        this.taskCenterPanel = new LeafTaskCenterPanel(this.homeFrameController, taskPanelDto);
    }

    @Override
    public boolean requestLoadContent() {
        return this.homeFrameController.requestLoadTaskContent(ControllerBase.LEAF_TASK_TYPE, getTaskId());
    }

    @Override
    protected boolean requestUpdate() {
        return this.homeFrameController.requestUpdateTaskPanel(
                ControllerBase.LEAF_TASK_TYPE, this, getTaskId());
    }
}
