package org.swing.app.view.home.components.leaftask.factory;

import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.home.components.TaskCenterPanel;
import org.swing.app.view.home.components.factory.TaskCenterPanelFactory;
import org.swing.app.view.home.components.roottask.RootTaskCenterPanel;

public class LeafTaskCenterPanelFactory implements TaskCenterPanelFactory {

    @Override
    public TaskCenterPanel createTaskCenterPanel(TaskPanelDto taskPanelDto) {
        return new RootTaskCenterPanel(taskPanelDto);
    }
}
