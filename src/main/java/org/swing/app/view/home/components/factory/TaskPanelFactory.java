package org.swing.app.view.home.components.factory;

import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.home.components.TaskPanel;

public interface TaskPanelFactory {

    TaskPanel createTaskPanel(TaskPanelDto taskPanelDto);
}
