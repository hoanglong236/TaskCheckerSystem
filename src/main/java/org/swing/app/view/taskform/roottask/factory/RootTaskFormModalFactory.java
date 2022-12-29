package org.swing.app.view.taskform.roottask.factory;

import org.swing.app.controller.TaskFormModalController;
import org.swing.app.dto.TaskDto;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.FrameWrapperComponent;
import org.swing.app.view.taskform.TaskFormModal;
import org.swing.app.view.taskform.factory.TaskFormModalFactory;
import org.swing.app.view.taskform.roottask.RootTaskFormModal;

public class RootTaskFormModalFactory implements TaskFormModalFactory {

    @Override
    public TaskFormModal createAddingTaskFormModal(FrameWrapperComponent parentFrame,
            TaskFormModalController taskFormModalController) {

        final TaskFormModal addingTaskFormModal = new RootTaskFormModal(parentFrame, taskFormModalController);
        addingTaskFormModal.resize(ViewConstant.ROOT_TASK_FORM_MODAL_PREFER_SIZE);
        return addingTaskFormModal;
    }

    @Override
    public TaskFormModal createUpdatingTaskFormModal(FrameWrapperComponent parentFrame,
            TaskFormModalController taskFormModalController, TaskDto taskDto) {

        final TaskFormModal updatingTaskFormModal =
                new RootTaskFormModal(parentFrame, taskFormModalController, taskDto);
        updatingTaskFormModal.resize(ViewConstant.ROOT_TASK_FORM_MODAL_PREFER_SIZE);
        return updatingTaskFormModal;
    }
}
