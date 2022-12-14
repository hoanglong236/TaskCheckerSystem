package org.swing.app.view.home.components.leaftask;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.home.components.taskbase.TaskPanel;
import org.swing.app.view.taskform.leaftask.factory.LeafTaskFormModalFactory;

public class LeafTaskPanel extends TaskPanel {

    public LeafTaskPanel(HomeFrameController homeFrameController, int preferHeight, TaskPanelDto taskPanelDto) {
        super(homeFrameController, new LeafTaskFormModalFactory(), preferHeight, taskPanelDto);
    }

    @Override
    protected boolean isNeedStatusChecker() {
        return true;
    }

    @Override
    protected boolean isNeedImportantLabel() {
        return false;
    }
}
