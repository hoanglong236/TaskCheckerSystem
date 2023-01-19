package org.swing.app.view.home.components.taskpanel;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.taskform.taskformmodal.factory.impl.RootTaskFormModalFactory;

public class RootTaskPanel extends TaskPanel {

    public RootTaskPanel(HomeFrameController homeFrameController, int preferHeight, TaskPanelDto taskPanelDto) {
        super(homeFrameController, new RootTaskFormModalFactory(), preferHeight, taskPanelDto);
    }

    @Override
    protected boolean isNeedStatusChecker() {
        return false;
    }

    @Override
    protected boolean isNeedImportantLabel() {
        return false;
    }
}
