package org.swing.app.view.home.components.taskcontentpanel;

import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.home.components.taskpanel.factory.TaskPanelFactory;
import org.swing.app.view.home.components.taskpanel.factory.impl.LeafTaskPanelFactory;
import org.swing.app.view.home.observer.taskcompletionrate.TaskCompletionRateEventSubject;
import org.swing.app.view.taskform.taskformmodal.factory.TaskFormModalFactory;
import org.swing.app.view.taskform.taskformmodal.factory.impl.LeafTaskFormModalFactory;

import java.util.Set;

public class NodeTaskContentPanel extends TaskContentPanel {

    public NodeTaskContentPanel(HomeFrameController homeFrameController,
            TaskPanelDto masterTaskPanelDto, Set<TaskPanelDto> taskPanelDtos,
            TaskCompletionRateEventSubject masterTaskCompletionRateEventSubject) {

        super(homeFrameController, masterTaskPanelDto, taskPanelDtos, masterTaskCompletionRateEventSubject);
    }

    @Override
    protected TaskPanelFactory createTaskPanelFactory() {
        return new LeafTaskPanelFactory();
    }

    @Override
    protected TaskFormModalFactory createTaskFormModalFactory() {
        return new LeafTaskFormModalFactory();
    }
}