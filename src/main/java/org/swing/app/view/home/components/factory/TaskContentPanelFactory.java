package org.swing.app.view.home.components.factory;

import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.home.components.TaskContentPanel;
import org.swing.app.view.home.components.nodetask.NodeTaskComponentFactory;
import org.swing.app.view.home.components.roottask.RootTaskComponentFactory;

import java.util.Set;

public class TaskContentPanelFactory {

    public static TaskContentPanel createRootTaskContentPanel(String title, Set<TaskPanelDto> taskPanelDtos) {
        return new TaskContentPanel(new RootTaskComponentFactory(), title, taskPanelDtos);
    }

    public static TaskContentPanel createNodeTaskContentPanel(String title, Set<TaskPanelDto> taskPanelDtos) {
        return new TaskContentPanel(new NodeTaskComponentFactory(), title, taskPanelDtos);
    }
}
