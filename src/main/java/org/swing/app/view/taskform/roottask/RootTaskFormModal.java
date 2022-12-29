package org.swing.app.view.taskform.roottask;

import org.swing.app.controller.TaskFormModalController;
import org.swing.app.dto.TaskDto;
import org.swing.app.view.components.FrameWrapperComponent;
import org.swing.app.view.taskform.TaskFormModal;
import org.swing.app.view.taskform.roottask.factory.RootTaskFormFactory;

public class RootTaskFormModal extends TaskFormModal {

    public RootTaskFormModal(FrameWrapperComponent parentFrame, TaskFormModalController taskFormModalController) {
        super(parentFrame, taskFormModalController, new RootTaskFormFactory());
    }

    public RootTaskFormModal(FrameWrapperComponent parentFrame, TaskFormModalController taskFormModalController,
            TaskDto taskDto) {

        super(parentFrame, taskFormModalController, new RootTaskFormFactory(), taskDto);
    }
}
