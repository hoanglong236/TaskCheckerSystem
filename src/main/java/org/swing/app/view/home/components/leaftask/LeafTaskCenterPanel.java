package org.swing.app.view.home.components.leaftask;

import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.home.components.TaskCenterPanel;

class LeafTaskCenterPanel extends TaskCenterPanel {

    public LeafTaskCenterPanel(TaskPanelDto taskPanelDto) {
        super(taskPanelDto);
    }

    @Override
    protected void loadOtherChildComponentsSize() {
    }

    @Override
    protected void setNotResizableChildComponents() {
    }
}
