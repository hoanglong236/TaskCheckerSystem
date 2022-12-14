package org.swing.app.view.home.components.leaftask;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.home.components.leaftask.factory.LeafTaskPanelContainerFactory;
import org.swing.app.view.home.components.leaftask.factory.LeafTaskPanelFactory;
import org.swing.app.view.home.components.taskbase.TaskPanelContainerWrapper;

import java.util.Set;

public class LeafTaskPanelContainerWrapper extends TaskPanelContainerWrapper {

    public LeafTaskPanelContainerWrapper(HomeFrameController homeFrameController,
            String title, Set<TaskPanelDto> taskPanelDtos) {

        super(homeFrameController, new LeafTaskPanelFactory(), new LeafTaskPanelContainerFactory(),
                title, taskPanelDtos);
    }
}
