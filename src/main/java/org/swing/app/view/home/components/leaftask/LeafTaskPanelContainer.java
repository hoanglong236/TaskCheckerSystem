package org.swing.app.view.home.components.leaftask;

import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.home.components.TaskPanelContainer;
import org.swing.app.view.home.components.leaftask.factory.LeafTaskPanelContainerChildComponentFactory;

import java.util.Set;

public class LeafTaskPanelContainer extends TaskPanelContainer {

    public LeafTaskPanelContainer(String title, Set<TaskPanelDto> taskPanelDtos) {
        super(new LeafTaskPanelContainerChildComponentFactory(), title, taskPanelDtos);
    }
}
