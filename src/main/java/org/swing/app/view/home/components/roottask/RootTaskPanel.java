package org.swing.app.view.home.components.roottask;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.home.components.taskbase.TaskPanel;
import org.swing.app.view.taskform.roottask.factory.RootTaskFormModalFactory;

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
