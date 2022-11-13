package org.swing.app.view.home.components.nodetask;

import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.home.components.TaskContentPanel;
import org.swing.app.view.home.components.nodetask.factory.NoteTaskPanelContainerFactory;

import java.util.Set;

public class NodeTaskContentPanel extends TaskContentPanel {

    public NodeTaskContentPanel(String title, Set<TaskPanelDto> taskPanelDtos) {
        super(new NoteTaskPanelContainerFactory(), title, taskPanelDtos);
    }
}
