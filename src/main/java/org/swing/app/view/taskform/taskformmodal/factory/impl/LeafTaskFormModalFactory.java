package org.swing.app.view.taskform.taskformmodal.factory.impl;

import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.FrameWrapperComponent;
import org.swing.app.view.taskform.taskformmodal.TaskFormModal;
import org.swing.app.view.taskform.taskformmodal.factory.TaskFormModalFactory;
import org.swing.app.view.taskform.taskformmodal.LeafTaskFormModal;

public class LeafTaskFormModalFactory extends TaskFormModalFactory {

    @Override
    protected TaskFormModal createTaskFormModal(FrameWrapperComponent parentFrame) {
        final TaskFormModal taskFormModal = new LeafTaskFormModal(parentFrame);
        taskFormModal.resize(ViewConstant.LEAF_TASK_FORM_MODAL_PREFER_SIZE);
        return taskFormModal;
    }
}