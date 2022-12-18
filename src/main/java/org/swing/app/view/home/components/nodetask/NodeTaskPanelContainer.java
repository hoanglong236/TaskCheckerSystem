package org.swing.app.view.home.components.nodetask;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.components.factory.UIComponentFactory;
import org.swing.app.view.home.components.taskviewport.TaskContainerViewportViewWithNotify;
import org.swing.app.view.home.components.nodetask.factory.NodeTaskPanelFactory;
import org.swing.app.view.home.components.taskbase.TaskPanelContainer;

import java.util.Set;

public class NodeTaskPanelContainer extends TaskPanelContainer {

    public NodeTaskPanelContainer(HomeFrameController homeFrameController,
            String title, Set<TaskPanelDto> taskPanelDtos) {

        super(homeFrameController, new NodeTaskPanelFactory(), title, taskPanelDtos);
    }

    @Override
    protected void initVerticalScrollPane(Set<TaskPanelDto> taskPanelDtos) {
        this.verticalScrollPane = UIComponentFactory.createVerticalScrollPane(
                new TaskContainerViewportViewWithNotify(this.homeFrameController));

        for (final TaskPanelDto taskPanelDto : taskPanelDtos) {
            addTaskPanelByDto(taskPanelDto);
        }
    }
}
