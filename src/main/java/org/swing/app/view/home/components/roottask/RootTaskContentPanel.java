package org.swing.app.view.home.components.roottask;

import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.home.components.TaskContentPanel;
import org.swing.app.view.home.components.roottask.factory.RootTaskPanelContainerFactory;

import java.util.Set;

public class RootTaskContentPanel extends TaskContentPanel {

    public RootTaskContentPanel( String title,
            Set<TaskPanelDto> taskPanelDtos) {
        super(new RootTaskPanelContainerFactory(), title, taskPanelDtos);
    }
}
