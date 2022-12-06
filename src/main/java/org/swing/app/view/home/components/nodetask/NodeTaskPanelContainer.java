package org.swing.app.view.home.components.nodetask;

import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.components.ui.UIComponentFactory;
import org.swing.app.view.home.components.TaskContainerViewportViewWithNotify;
import org.swing.app.view.home.components.TaskPanelContainer;

import java.util.Set;

class NodeTaskPanelContainer extends TaskPanelContainer {

    public NodeTaskPanelContainer(String title, Set<TaskPanelDto> taskPanelDtos) {
        super(new NodeTaskPanelFactory(), title, taskPanelDtos);
    }

    @Override
    protected void initVerticalScrollPane(Set<TaskPanelDto> taskPanelDtos) {
        this.verticalScrollPane = UIComponentFactory.createVerticalScrollPane(
                new TaskContainerViewportViewWithNotify());
    }
}
