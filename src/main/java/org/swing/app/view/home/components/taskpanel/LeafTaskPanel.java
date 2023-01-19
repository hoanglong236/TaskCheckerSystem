package org.swing.app.view.home.components.taskpanel;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.taskform.taskformmodal.factory.impl.LeafTaskFormModalFactory;

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
