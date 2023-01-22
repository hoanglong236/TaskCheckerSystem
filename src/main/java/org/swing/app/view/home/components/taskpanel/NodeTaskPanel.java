package org.swing.app.view.home.components.taskpanel;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.common.ComponentSizeConstants;
import org.swing.app.view.taskform.taskformmodal.factory.TaskFormModalFactory;
import org.swing.app.view.taskform.taskformmodal.factory.impl.NodeTaskFormModalFactory;

public class NodeTaskPanel extends TaskPanel {

    public NodeTaskPanel(HomeFrameController homeFrameController, TaskPanelDto taskPanelDto) {
        super(homeFrameController, taskPanelDto);
    }

    @Override
    public int getPreferHeight() {
        return ComponentSizeConstants.NODE_TASK_PANEL_HEIGHT;
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
    protected TaskFormModalFactory createTaskFormModalFactory() {
        return new NodeTaskFormModalFactory();
    }
}
