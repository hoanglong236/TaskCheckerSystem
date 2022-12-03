package org.swing.app.view.home.components.factory;

import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.home.components.TaskContentPanel;
import org.swing.app.view.home.components.nodetask.factory.NoteTaskPanelContainerFactory;
import org.swing.app.view.home.components.roottask.factory.RootTaskPanelContainerFactory;

import java.util.Set;

public class TaskContentPanelFactory {

    public static TaskContentPanel createRootTaskContentPanel(String title, Set<TaskPanelDto> taskPanelDtos) {
        return new TaskContentPanel(new RootTaskPanelContainerFactory(), title, taskPanelDtos);
    }

    public static TaskContentPanel createNodeTaskContentPanel(String title, Set<TaskPanelDto> taskPanelDtos) {
        return new TaskContentPanel(new NoteTaskPanelContainerFactory(), title, taskPanelDtos);
    }
}
