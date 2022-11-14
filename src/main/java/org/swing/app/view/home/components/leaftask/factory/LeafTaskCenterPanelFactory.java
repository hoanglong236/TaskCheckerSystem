package org.swing.app.view.home.components.leaftask.factory;

import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.home.components.TaskCenterPanel;
import org.swing.app.view.home.components.factory.TaskCenterPanelFactory;

public class LeafTaskCenterPanelFactory implements TaskCenterPanelFactory {

    @Override
    public TaskCenterPanel createTaskCenterPanel(TaskPanelDto taskPanelDto) {
        return new LeafTaskCenterPanel(taskPanelDto);
    }
}

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