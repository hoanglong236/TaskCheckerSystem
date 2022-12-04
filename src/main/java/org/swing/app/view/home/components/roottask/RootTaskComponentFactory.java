package org.swing.app.view.home.components.roottask;

import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.components.ui.UIComponentFactory;
import org.swing.app.view.components.ui.VerticalScrollPane;
import org.swing.app.view.home.components.TaskCenterPanel;
import org.swing.app.view.home.components.TaskPanel;
import org.swing.app.view.home.components.TaskPanelContainer;
import org.swing.app.view.home.components.factory.TaskComponentFactory;

import java.util.Set;

public class RootTaskComponentFactory implements TaskComponentFactory {

    @Override
    public TaskCenterPanel createTaskCenterPanel(TaskPanelDto taskPanelDto) {
        return new RootTaskCenterPanel(taskPanelDto);
    }

    @Override
    public TaskPanel createTaskPanel(TaskPanelDto taskPanelDto) {
        return new RootTaskPanel(this, taskPanelDto);
    }

    @Override
    public VerticalScrollPane createScrollPaneToContainTaskPanels() {
        return UIComponentFactory.createVerticalScrollPane();
    }

    @Override
    public TaskPanelContainer createTaskPanelContainer(String title, Set<TaskPanelDto> taskPanelDtos) {
        return new TaskPanelContainer(this, title, taskPanelDtos);
    }
}
