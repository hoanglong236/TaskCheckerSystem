package org.swing.app.view.home.components.nodetask.factory;

import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.components.ui.UIComponentFactory;
import org.swing.app.view.components.ui.VerticalScrollPane;
import org.swing.app.view.home.components.TaskPanel;
import org.swing.app.view.home.components.factory.TaskPanelContainerChildComponentFactory;
import org.swing.app.view.home.components.roottask.RootTaskPanel;

class NodeTaskPanelContainerChildComponentFactory implements TaskPanelContainerChildComponentFactory {

    @Override
    public TaskPanel createTaskPanel(TaskPanelDto taskPanelDto) {
        return new RootTaskPanel(taskPanelDto, new NoteTaskCenterPanelFactory());
    }

    @Override
    public VerticalScrollPane createVerticalScrollPane() {
        return UIComponentFactory.createVerticalScrollPaneWithViewportNotify();
    }
}
