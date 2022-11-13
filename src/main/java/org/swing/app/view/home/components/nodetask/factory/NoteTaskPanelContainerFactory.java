package org.swing.app.view.home.components.nodetask.factory;

import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.home.components.TaskPanelContainer;
import org.swing.app.view.home.components.factory.TaskPanelContainerFactory;
import org.swing.app.view.home.components.nodetask.NodeTaskPanelContainer;

import java.util.Set;

public class NoteTaskPanelContainerFactory implements TaskPanelContainerFactory {

    @Override
    public TaskPanelContainer createTaskPanelContainer(String title, Set<TaskPanelDto> taskPanelDtos) {
        return new NodeTaskPanelContainer(title, taskPanelDtos);
    }
}
