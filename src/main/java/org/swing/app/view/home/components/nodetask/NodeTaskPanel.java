package org.swing.app.view.home.components.nodetask;

import org.swing.app.controller.ControllerBase;
import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.home.components.taskbase.TaskPanel;
import org.swing.app.view.home.components.taskbase.TaskPanelManagerComponent;

public class NodeTaskPanel extends TaskPanel {

    public NodeTaskPanel(HomeFrameController homeFrameController, TaskPanelManagerComponent taskPanelManager,
            int preferHeight, TaskPanelDto taskPanelDto) {

        super(homeFrameController, taskPanelManager, preferHeight, taskPanelDto);
    }

    @Override
    protected boolean isNeedStatusChecker() {
        return true;
    }

    @Override
    protected boolean isNeedImportantLabel() {
        return true;
    }

    @Override
    public byte getTaskTypeToRequest() {
        return ControllerBase.NODE_TASK_TYPE;
    }

    @Override
    protected void initTaskCenterPanel(TaskPanelDto taskPanelDto) {
        this.taskCenterPanel = new NodeTaskCenterPanel(this.homeFrameController, taskPanelDto);
    }
}
