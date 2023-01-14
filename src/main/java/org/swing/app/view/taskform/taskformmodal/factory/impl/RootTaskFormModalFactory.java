package org.swing.app.view.taskform.taskformmodal.factory.impl;

import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.FrameWrapperComponent;
import org.swing.app.view.taskform.taskformmodal.TaskFormModal;
import org.swing.app.view.taskform.taskformmodal.factory.TaskFormModalFactory;
import org.swing.app.view.taskform.taskformmodal.RootTaskFormModal;

public class RootTaskFormModalFactory extends TaskFormModalFactory {

    @Override
    protected TaskFormModal createTaskFormModal(FrameWrapperComponent parentFrame) {
        final TaskFormModal taskFormModal = new RootTaskFormModal(parentFrame);
        taskFormModal.resize(ViewConstant.ROOT_TASK_FORM_MODAL_PREFER_SIZE);
        return taskFormModal;
    }
}
