package org.swing.app.view.home.components.roottask;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.components.factory.UIComponentFactory;
import org.swing.app.view.home.components.taskviewport.TaskContainerViewportViewWithoutNotify;
import org.swing.app.view.home.components.roottask.factory.RootTaskPanelFactory;
import org.swing.app.view.home.components.taskbase.TaskPanelContainer;

import java.util.Set;

public class RootTaskPanelContainer extends TaskPanelContainer {

    public RootTaskPanelContainer(HomeFrameController homeFrameController,
            String title, Set<TaskPanelDto> taskPanelDtos) {

        super(homeFrameController, new RootTaskPanelFactory(), title, taskPanelDtos);
    }

    @Override
    protected void initVerticalScrollPane(Set<TaskPanelDto> taskPanelDtos) {
        this.verticalScrollPane = UIComponentFactory.createVerticalScrollPane(
                new TaskContainerViewportViewWithoutNotify(this.homeFrameController));

        for (final TaskPanelDto taskPanelDto : taskPanelDtos) {
            addTaskPanelByDto(taskPanelDto);
        }
    }
}
