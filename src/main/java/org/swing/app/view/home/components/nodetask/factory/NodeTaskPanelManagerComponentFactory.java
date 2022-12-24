package org.swing.app.view.home.components.nodetask.factory;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.home.components.nodetask.NodeTaskPanelManagerComponent;
import org.swing.app.view.home.components.taskbase.TaskPanelManagerComponent;
import org.swing.app.view.home.components.factory.TaskPanelManagerComponentFactory;

import java.util.Set;

public class NodeTaskPanelManagerComponentFactory implements TaskPanelManagerComponentFactory {

    @Override
    public TaskPanelManagerComponent createTaskPanelManagerComponent(HomeFrameController homeFrameController,
            String title, Set<TaskPanelDto> taskPanelDtos) {

        return new NodeTaskPanelManagerComponent(homeFrameController, title, taskPanelDtos);
    }
}
