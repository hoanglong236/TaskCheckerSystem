package org.swing.app.view.home.components.leaftask;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.components.factory.UIComponentFactory;
import org.swing.app.view.home.components.TaskContainerViewportViewWithNotify;
import org.swing.app.view.home.components.leaftask.factory.LeafTaskPanelFactory;
import org.swing.app.view.home.components.taskbase.TaskPanelContainer;

import java.util.Set;

public class LeafTaskPanelContainer extends TaskPanelContainer {

    public LeafTaskPanelContainer(HomeFrameController homeFrameController,
            String title, Set<TaskPanelDto> taskPanelDtos) {

        super(homeFrameController, new LeafTaskPanelFactory(), title, taskPanelDtos);
    }

    @Override
    protected void initVerticalScrollPane(Set<TaskPanelDto> taskPanelDtos) {
        this.verticalScrollPane = UIComponentFactory.createVerticalScrollPane(
                new TaskContainerViewportViewWithNotify());
    }
}
