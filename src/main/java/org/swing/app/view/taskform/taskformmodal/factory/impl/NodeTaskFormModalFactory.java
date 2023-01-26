package org.swing.app.view.taskform.taskformmodal.factory.impl;

import org.swing.app.view.common.ComponentSizeConstants;
import org.swing.app.view.components.ViewComponent;
import org.swing.app.view.taskform.taskformmodal.TaskFormModal;
import org.swing.app.view.taskform.taskformmodal.factory.TaskFormModalFactory;
import org.swing.app.view.taskform.taskformmodal.NodeTaskFormModal;

public class NodeTaskFormModalFactory extends TaskFormModalFactory {

    @Override
    protected TaskFormModal createTaskFormModal(ViewComponent windowComponent) {
        final TaskFormModal taskFormModal = new NodeTaskFormModal(windowComponent);
        taskFormModal.resize(ComponentSizeConstants.NODE_TASK_FORM_MODAL_PREFER_SIZE);
        return taskFormModal;
    }
}
