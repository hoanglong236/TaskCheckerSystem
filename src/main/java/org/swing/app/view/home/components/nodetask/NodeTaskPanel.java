package org.swing.app.view.home.components.nodetask;

import org.swing.app.controller.ControllerBase;
import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.home.components.taskbase.TaskPanel;

public class NodeTaskPanel extends TaskPanel {

    public NodeTaskPanel(HomeFrameController homeFrameController, int preferHeight, TaskPanelDto taskPanelDto) {
        super(homeFrameController, preferHeight, taskPanelDto);
    }

    @Override
    protected boolean hasStatusChecker() {
        return true;
    }

    @Override
    protected boolean hasImportantLabel() {
        return true;
    }

    @Override
    protected void initTaskCenterPanel(TaskPanelDto taskPanelDto) {
        this.taskCenterPanel = new NodeTaskCenterPanel(this.homeFrameController, taskPanelDto);
    }

    @Override
    public boolean requestLoadContent() {
        return this.homeFrameController.requestLoadTaskContent(ControllerBase.NODE_TASK_TYPE, getTaskId());
    }

    @Override
    protected boolean requestUpdate() {
        return this.homeFrameController.requestUpdateTaskPanel(
                ControllerBase.NODE_TASK_TYPE, this);
    }
}
