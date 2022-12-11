package org.swing.app.view.home.components.roottask;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.util.MessageLoader;
import org.swing.app.view.components.ui.UIComponentFactory;
import org.swing.app.view.home.components.taskbase.TaskContentPanel;
import org.swing.app.view.home.components.nodetask.factory.NodeTaskPanelContainerFactory;

import java.util.Set;

public class RootTaskContentPanel extends TaskContentPanel {

    public RootTaskContentPanel(HomeFrameController homeFrameController,
            String title, Set<TaskPanelDto> taskPanelDtos) {

        super(homeFrameController, new NodeTaskPanelContainerFactory(), title, taskPanelDtos);
    }

    @Override
    protected void initAddNewTaskComponent() {
        final MessageLoader messageLoader = MessageLoader.getInstance();
        this.addNewTaskComponent = UIComponentFactory.createButton(
                messageLoader.getMessage("add.task.component.text"));
    }
}
