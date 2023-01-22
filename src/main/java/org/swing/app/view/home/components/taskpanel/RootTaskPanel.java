package org.swing.app.view.home.components.taskpanel;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.common.ComponentSizeConstants;
import org.swing.app.view.taskform.taskformmodal.factory.TaskFormModalFactory;
import org.swing.app.view.taskform.taskformmodal.factory.impl.RootTaskFormModalFactory;

public class RootTaskPanel extends TaskPanel {

    public RootTaskPanel(HomeFrameController homeFrameController, TaskPanelDto taskPanelDto) {
        super(homeFrameController, taskPanelDto);
    }

    @Override
    public int getPreferHeight() {
        return ComponentSizeConstants.ROOT_TASK_PANEL_HEIGHT;
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
    protected TaskFormModalFactory createTaskFormModalFactory() {
        return new RootTaskFormModalFactory();
    }
}
