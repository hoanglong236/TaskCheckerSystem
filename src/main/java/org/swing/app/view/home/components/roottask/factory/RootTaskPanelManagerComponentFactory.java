package org.swing.app.view.home.components.roottask.factory;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.home.components.roottask.RootTaskPanelManagerComponent;
import org.swing.app.view.home.components.taskbase.TaskPanelManagerComponent;
import org.swing.app.view.home.components.factory.TaskPanelManagerComponentFactory;

import java.util.Set;

public class RootTaskPanelManagerComponentFactory implements TaskPanelManagerComponentFactory {

    @Override
    public TaskPanelManagerComponent createTaskPanelManagerComponent(HomeFrameController homeFrameController,
            String title, Set<TaskPanelDto> taskPanelDtos) {

        return new RootTaskPanelManagerComponent(homeFrameController, title, taskPanelDtos);
    }
}
