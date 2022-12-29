package org.swing.app.view.taskform.leaftask.factory;

import org.swing.app.controller.TaskFormModalController;
import org.swing.app.dto.TaskDto;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.FrameWrapperComponent;
import org.swing.app.view.taskform.TaskFormModal;
import org.swing.app.view.taskform.factory.TaskFormModalFactory;
import org.swing.app.view.taskform.leaftask.LeafTaskFormModal;

public class LeafTaskFormModalFactory implements TaskFormModalFactory {

    @Override
    public TaskFormModal createAddingTaskFormModal(FrameWrapperComponent parentFrame,
            TaskFormModalController taskFormModalController) {

        final TaskFormModal addingTaskFormModal = new LeafTaskFormModal(parentFrame, taskFormModalController);
        addingTaskFormModal.resize(ViewConstant.LEAF_TASK_FORM_MODAL_PREFER_SIZE);
        return addingTaskFormModal;
    }

    @Override
    public TaskFormModal createUpdatingTaskFormModal(FrameWrapperComponent parentFrame,
            TaskFormModalController taskFormModalController, TaskDto taskDto) {

        final TaskFormModal updatingTaskFormModal =
                new LeafTaskFormModal(parentFrame, taskFormModalController, taskDto);
        updatingTaskFormModal.resize(ViewConstant.LEAF_TASK_FORM_MODAL_PREFER_SIZE);
        return updatingTaskFormModal;
    }
}
