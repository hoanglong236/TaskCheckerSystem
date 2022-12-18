package org.swing.app.view.home.components.roottask;

import org.swing.app.controller.ControllerBase;
import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.home.components.taskbase.TaskPanel;

public class RootTaskPanel extends TaskPanel {

    public RootTaskPanel(HomeFrameController homeFrameController, int preferHeight, TaskPanelDto taskPanelDto) {
        super(homeFrameController, preferHeight, taskPanelDto);
    }

    @Override
    protected boolean isNeedStatusChecker() {
        return false;
    }

    @Override
    protected boolean isNeedImportantLabel() {
        return false;
    }

    @Override
    protected void initTaskCenterPanel(TaskPanelDto taskPanelDto) {
        this.taskCenterPanel = new RootTaskCenterPanel(this.homeFrameController, taskPanelDto);
    }

    @Override
    public boolean requestLoadContent() {
        return this.homeFrameController.requestLoadTaskContent(ControllerBase.ROOT_TASK_TYPE, getTaskId());
    }

    @Override
    protected boolean requestUpdate() {
        return this.homeFrameController.requestUpdateTaskPanel(
                ControllerBase.ROOT_TASK_TYPE, this);
    }
}
