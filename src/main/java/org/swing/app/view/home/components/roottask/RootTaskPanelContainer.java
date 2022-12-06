package org.swing.app.view.home.components.roottask;

import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.components.ui.UIComponentFactory;
import org.swing.app.view.home.components.TaskContainerViewportView;
import org.swing.app.view.home.components.TaskPanelContainer;

import java.util.Set;

class RootTaskPanelContainer extends TaskPanelContainer {

    public RootTaskPanelContainer(String title, Set<TaskPanelDto> taskPanelDtos) {
        super(new RootTaskPanelFactory(), title, taskPanelDtos);
    }

    @Override
    protected void initVerticalScrollPane(Set<TaskPanelDto> taskPanelDtos) {
        this.verticalScrollPane = UIComponentFactory.createVerticalScrollPane(new TaskContainerViewportView());
    }
}
