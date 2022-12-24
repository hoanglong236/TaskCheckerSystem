package org.swing.app.view.home.components.roottask;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.home.components.roottask.factory.RootTaskPanelContainerFactory;
import org.swing.app.view.home.components.roottask.factory.RootTaskPanelFactory;
import org.swing.app.view.home.components.taskbase.TaskPanelManagerComponent;

import java.util.Set;

public class RootTaskPanelManagerComponent extends TaskPanelManagerComponent {

    public RootTaskPanelManagerComponent(HomeFrameController homeFrameController,
            String title, Set<TaskPanelDto> taskPanelDtos) {

        super(homeFrameController, new RootTaskPanelFactory(), new RootTaskPanelContainerFactory(),
                title, taskPanelDtos);
    }
}
