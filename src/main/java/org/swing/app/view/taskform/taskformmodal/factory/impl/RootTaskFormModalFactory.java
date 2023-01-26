package org.swing.app.view.taskform.taskformmodal.factory.impl;

import org.swing.app.view.common.ComponentSizeConstants;
import org.swing.app.view.components.ViewComponent;
import org.swing.app.view.taskform.taskformmodal.TaskFormModal;
import org.swing.app.view.taskform.taskformmodal.factory.TaskFormModalFactory;
import org.swing.app.view.taskform.taskformmodal.RootTaskFormModal;

public class RootTaskFormModalFactory extends TaskFormModalFactory {

    @Override
    protected TaskFormModal createTaskFormModal(ViewComponent windowComponent) {
        final TaskFormModal taskFormModal = new RootTaskFormModal(windowComponent);
        taskFormModal.resize(ComponentSizeConstants.ROOT_TASK_FORM_MODAL_PREFER_SIZE);
        return taskFormModal;
    }
}
