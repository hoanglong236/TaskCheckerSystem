package org.swing.app.view.home.components.roottask;

import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.home.components.TaskPanel;
import org.swing.app.view.home.components.factory.TaskPanelFactory;

public class RootTaskPanelFactory implements TaskPanelFactory {

    @Override
    public TaskPanel createTaskPanel(TaskPanelDto taskPanelDto) {
        return new RootTaskPanel(ViewConstant.ROOT_TASK_PANEL_HEIGHT, taskPanelDto);
    }
}
