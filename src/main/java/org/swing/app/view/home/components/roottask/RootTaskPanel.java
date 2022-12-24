package org.swing.app.view.home.components.roottask;

import org.swing.app.controller.ControllerBase;
import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.home.components.taskbase.TaskPanel;
import org.swing.app.view.home.components.taskbase.TaskPanelManagerComponent;

public class RootTaskPanel extends TaskPanel {

    public RootTaskPanel(HomeFrameController homeFrameController, TaskPanelManagerComponent taskPanelManager,
            int preferHeight, TaskPanelDto taskPanelDto) {

        super(homeFrameController, taskPanelManager, preferHeight, taskPanelDto);
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
    public byte getTaskTypeToRequest() {
        return ControllerBase.ROOT_TASK_TYPE;
    }

    @Override
    protected void initTaskCenterPanel(TaskPanelDto taskPanelDto) {
        this.taskCenterPanel = new RootTaskCenterPanel(this.homeFrameController, taskPanelDto);
    }
}
