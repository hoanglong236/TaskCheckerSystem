package org.swing.app.view.taskform.leaftask;

import org.swing.app.controller.TaskFormModalController;
import org.swing.app.dto.TaskDto;
import org.swing.app.view.components.FrameWrapperComponent;
import org.swing.app.view.taskform.TaskFormModal;
import org.swing.app.view.taskform.leaftask.factory.LeafTaskFormFactory;

public class LeafTaskFormModal extends TaskFormModal {

    public LeafTaskFormModal(FrameWrapperComponent parentFrame, TaskFormModalController taskFormModalController) {
        super(parentFrame, taskFormModalController, new LeafTaskFormFactory());
    }

    public LeafTaskFormModal(FrameWrapperComponent parentFrame, TaskFormModalController taskFormModalController,
            TaskDto taskDto) {

        super(parentFrame, taskFormModalController, new LeafTaskFormFactory(), taskDto);
    }
}
