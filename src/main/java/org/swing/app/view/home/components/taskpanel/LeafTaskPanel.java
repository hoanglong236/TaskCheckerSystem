package org.swing.app.view.home.components.taskpanel;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.common.ComponentSizeConstants;
import org.swing.app.view.taskform.taskformmodal.factory.TaskFormModalFactory;
import org.swing.app.view.taskform.taskformmodal.factory.impl.LeafTaskFormModalFactory;

public class LeafTaskPanel extends TaskPanel {

    public LeafTaskPanel(HomeFrameController homeFrameController, TaskPanelDto taskPanelDto) {
        super(homeFrameController, taskPanelDto);
    }

    @Override
    public int getPreferHeight() {
        return ComponentSizeConstants.LEAF_TASK_PANEL_HEIGHT;
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
    protected TaskFormModalFactory createTaskFormModalFactory() {
        return new LeafTaskFormModalFactory();
    }
}
