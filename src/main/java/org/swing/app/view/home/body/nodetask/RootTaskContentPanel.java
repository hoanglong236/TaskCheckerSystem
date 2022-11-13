package org.swing.app.view.home.body.nodetask;

import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.home.components.TaskContentPanel;
import org.swing.app.view.home.components.factory.TaskPanelContainerFactory;

import java.util.Set;

public class RootTaskContentPanel extends TaskContentPanel {

    public RootTaskContentPanel(
            TaskPanelContainerFactory taskPanelContainerFactory, String title,
            Set<TaskPanelDto> taskPanelDtos) {
        super(taskPanelContainerFactory, title, taskPanelDtos);
    }
}
