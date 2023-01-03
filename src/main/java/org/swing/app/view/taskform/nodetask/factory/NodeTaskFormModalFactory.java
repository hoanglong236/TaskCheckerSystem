package org.swing.app.view.taskform.nodetask.factory;

import org.swing.app.dto.TaskDto;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.FrameWrapperComponent;
import org.swing.app.view.taskform.TaskFormModal;
import org.swing.app.view.taskform.factory.TaskFormModalFactory;
import org.swing.app.view.taskform.nodetask.NodeTaskFormModal;

public class NodeTaskFormModalFactory extends TaskFormModalFactory {

    @Override
    protected TaskFormModal createAddingTaskFormModal(FrameWrapperComponent parentFrame) {
        final TaskFormModal addingTaskFormModal = new NodeTaskFormModal(parentFrame);
        addingTaskFormModal.resize(ViewConstant.NODE_TASK_FORM_MODAL_PREFER_SIZE);
        return addingTaskFormModal;
    }

    @Override
    protected TaskFormModal createUpdatingTaskFormModal(FrameWrapperComponent parentFrame, TaskDto taskDto) {
        final TaskFormModal updatingTaskFormModal = new NodeTaskFormModal(parentFrame, taskDto);
        updatingTaskFormModal.resize(ViewConstant.NODE_TASK_FORM_MODAL_PREFER_SIZE);
        return updatingTaskFormModal;
    }
}
