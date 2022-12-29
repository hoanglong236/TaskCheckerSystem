package org.swing.app.view.taskform.nodetask.factory;

import org.swing.app.controller.TaskFormModalController;
import org.swing.app.dto.TaskDto;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.FrameWrapperComponent;
import org.swing.app.view.taskform.TaskFormModal;
import org.swing.app.view.taskform.factory.TaskFormModalFactory;
import org.swing.app.view.taskform.nodetask.NodeTaskFormModal;

public class NodeTaskFormModalFactory implements TaskFormModalFactory {

    @Override
    public TaskFormModal createAddingTaskFormModal(FrameWrapperComponent parentFrame,
            TaskFormModalController taskFormModalController) {

        final TaskFormModal addingTaskFormModal = new NodeTaskFormModal(parentFrame, taskFormModalController);
        addingTaskFormModal.resize(ViewConstant.NODE_TASK_FORM_MODAL_PREFER_SIZE);
        return addingTaskFormModal;
    }

    @Override
    public TaskFormModal createUpdatingTaskFormModal(FrameWrapperComponent parentFrame,
            TaskFormModalController taskFormModalController, TaskDto taskDto) {

        final TaskFormModal updatingTaskFormModal =
                new NodeTaskFormModal(parentFrame, taskFormModalController, taskDto);
        updatingTaskFormModal.resize(ViewConstant.NODE_TASK_FORM_MODAL_PREFER_SIZE);
        return updatingTaskFormModal;
    }
}
