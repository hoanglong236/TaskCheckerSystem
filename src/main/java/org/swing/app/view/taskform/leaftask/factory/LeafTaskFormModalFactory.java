package org.swing.app.view.taskform.leaftask.factory;

import org.swing.app.dto.TaskDto;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.FrameWrapperComponent;
import org.swing.app.view.taskform.TaskFormModal;
import org.swing.app.view.taskform.factory.TaskFormModalFactory;
import org.swing.app.view.taskform.leaftask.LeafTaskFormModal;

public class LeafTaskFormModalFactory extends TaskFormModalFactory {

    @Override
    protected TaskFormModal createAddingTaskFormModal(FrameWrapperComponent parentFrame) {
        final TaskFormModal addingTaskFormModal = new LeafTaskFormModal(parentFrame);
        addingTaskFormModal.resize(ViewConstant.LEAF_TASK_FORM_MODAL_PREFER_SIZE);
        return addingTaskFormModal;
    }

    @Override
    protected TaskFormModal createUpdatingTaskFormModal(FrameWrapperComponent parentFrame, TaskDto taskDto) {
        final TaskFormModal updatingTaskFormModal = new LeafTaskFormModal(parentFrame);
        updatingTaskFormModal.resize(ViewConstant.LEAF_TASK_FORM_MODAL_PREFER_SIZE);
        return updatingTaskFormModal;
    }
}
