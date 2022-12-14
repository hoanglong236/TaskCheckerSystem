package org.swing.app.view.home.components.nodetask;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.home.components.taskbase.TaskPanel;
import org.swing.app.view.taskform.nodetask.factory.NodeTaskFormModalFactory;

public class NodeTaskPanel extends TaskPanel {

    public NodeTaskPanel(HomeFrameController homeFrameController, int preferHeight, TaskPanelDto taskPanelDto) {
        super(homeFrameController, new NodeTaskFormModalFactory(), preferHeight, taskPanelDto);
    }

    @Override
    protected boolean isNeedStatusChecker() {
        return true;
    }

    @Override
    protected boolean isNeedImportantLabel() {
        return true;
    }
}
