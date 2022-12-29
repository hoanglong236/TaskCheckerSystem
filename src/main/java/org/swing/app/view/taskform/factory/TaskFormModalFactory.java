package org.swing.app.view.taskform.factory;

import org.swing.app.controller.TaskFormModalController;
import org.swing.app.dto.TaskDto;
import org.swing.app.view.components.FrameWrapperComponent;
import org.swing.app.view.taskform.TaskFormModal;

public interface TaskFormModalFactory {

    TaskFormModal createAddingTaskFormModal(FrameWrapperComponent parentFrame,
            TaskFormModalController taskFormModalController);
    TaskFormModal createUpdatingTaskFormModal(FrameWrapperComponent parentFrame,
            TaskFormModalController taskFormModalController, TaskDto taskDto);
}
