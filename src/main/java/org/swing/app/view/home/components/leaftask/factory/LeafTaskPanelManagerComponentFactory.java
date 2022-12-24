package org.swing.app.view.home.components.leaftask.factory;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.home.components.leaftask.LeafTaskPanelManagerComponent;
import org.swing.app.view.home.components.taskbase.TaskPanelManagerComponent;
import org.swing.app.view.home.components.factory.TaskPanelManagerComponentFactory;

import java.util.Set;

public class LeafTaskPanelManagerComponentFactory implements TaskPanelManagerComponentFactory {

    @Override
    public TaskPanelManagerComponent createTaskPanelManagerComponent(HomeFrameController homeFrameController,
            String title, Set<TaskPanelDto> taskPanelDtos) {

        return new LeafTaskPanelManagerComponent(homeFrameController, title, taskPanelDtos);
    }
}
