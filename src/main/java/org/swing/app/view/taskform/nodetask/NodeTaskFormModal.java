package org.swing.app.view.taskform.nodetask;

import org.swing.app.controller.TaskFormModalController;
import org.swing.app.dto.TaskDto;
import org.swing.app.view.components.FrameWrapperComponent;
import org.swing.app.view.taskform.TaskFormModal;
import org.swing.app.view.taskform.nodetask.factory.NodeTaskFormFactory;

public class NodeTaskFormModal extends TaskFormModal {

    public NodeTaskFormModal(FrameWrapperComponent parentFrame, TaskFormModalController taskFormModalController) {
        super(parentFrame, taskFormModalController, new NodeTaskFormFactory());
    }

    public NodeTaskFormModal(FrameWrapperComponent parentFrame, TaskFormModalController taskFormModalController,
            TaskDto taskDto) {

        super(parentFrame, taskFormModalController, new NodeTaskFormFactory(), taskDto);
    }
}
