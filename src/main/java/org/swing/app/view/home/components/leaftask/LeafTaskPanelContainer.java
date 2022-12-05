package org.swing.app.view.home.components.leaftask;

import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.components.ui.UIComponentFactory;
import org.swing.app.view.home.components.TaskPanelContainer;

import java.util.Set;

class LeafTaskPanelContainer extends TaskPanelContainer {

    public LeafTaskPanelContainer(String title, Set<TaskPanelDto> taskPanelDtos) {
        super(new LeafTaskPanelFactory(), title, taskPanelDtos);
    }

    @Override
    protected void initVerticalScrollPane(Set<TaskPanelDto> taskPanelDtos) {
        this.verticalScrollPane = UIComponentFactory.createVerticalScrollPaneWithViewportNotify();
    }
}
