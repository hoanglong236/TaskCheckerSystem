package org.swing.app.view.home.components.factory;

import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.components.ui.VerticalScrollPane;
import org.swing.app.view.home.components.TaskPanel;

public interface TaskPanelContainerChildComponentFactory {

    TaskPanel createTaskPanel(TaskPanelDto taskPanelDto);
    VerticalScrollPane createVerticalScrollPane();
}
