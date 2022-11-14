package org.swing.app.view.home.components.leaftask.factory;

import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.components.ui.VerticalScrollPane;
import org.swing.app.view.home.components.TaskPanel;
import org.swing.app.view.home.components.VerticalViewportViewWithNotify;
import org.swing.app.view.home.components.factory.TaskPanelContainerChildComponentFactory;
import org.swing.app.view.home.components.roottask.RootTaskPanel;

class LeafTaskPanelContainerChildComponentFactory implements TaskPanelContainerChildComponentFactory {

    @Override
    public TaskPanel createTaskPanel(TaskPanelDto taskPanelDto) {
        return new RootTaskPanel(taskPanelDto, new LeafTaskCenterPanelFactory());
    }

    @Override
    public VerticalScrollPane createVerticalScrollPane() {
        return new VerticalScrollPane(new VerticalViewportViewWithNotify());
    }
}
