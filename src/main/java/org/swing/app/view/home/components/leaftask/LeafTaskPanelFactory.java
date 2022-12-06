package org.swing.app.view.home.components.leaftask;

import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.home.components.TaskPanel;
import org.swing.app.view.home.components.factory.TaskPanelFactory;

public class LeafTaskPanelFactory implements TaskPanelFactory {

    @Override
    public TaskPanel createTaskPanel(TaskPanelDto taskPanelDto) {
        return new LeafTaskPanel(ViewConstant.LEAF_TASK_PANEL_HEIGHT, taskPanelDto);
    }
}
