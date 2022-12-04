package org.swing.app.view.home.components.leaftask;

import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.components.ui.UIComponentFactory;
import org.swing.app.view.components.ui.VerticalScrollPane;
import org.swing.app.view.home.components.TaskCenterPanel;
import org.swing.app.view.home.components.TaskPanel;
import org.swing.app.view.home.components.TaskPanelContainer;
import org.swing.app.view.home.components.factory.TaskComponentFactory;

import java.util.Set;

public class LeafTaskComponentFactory implements TaskComponentFactory {

    @Override
    public TaskCenterPanel createTaskCenterPanel(TaskPanelDto taskPanelDto) {
        return new LeafTaskCenterPanel(taskPanelDto);
    }

    @Override
    public TaskPanel createTaskPanel(TaskPanelDto taskPanelDto) {
        return new LeafTaskPanel(this, taskPanelDto);
    }

    @Override
    public VerticalScrollPane createScrollPaneToContainTaskPanels() {
        return UIComponentFactory.createVerticalScrollPaneWithViewportNotify();
    }

    @Override
    public TaskPanelContainer createTaskPanelContainer(String title, Set<TaskPanelDto> taskPanelDtos) {
        return new TaskPanelContainer(this, title, taskPanelDtos);
    }
}
