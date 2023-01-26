package org.swing.app.view.taskform.taskformmodal.factory;

import org.swing.app.dto.TaskDto;
import org.swing.app.util.MessageLoader;
import org.swing.app.view.components.ViewComponent;
import org.swing.app.view.components.modal.ModalWrapperComponent;
import org.swing.app.view.taskform.taskformmodal.TaskFormModal;

import java.util.Optional;

public abstract class TaskFormModalFactory {

    protected abstract TaskFormModal createTaskFormModal(ViewComponent windowComponent);

    public Optional<TaskDto> showAddingTaskFormModal(ViewComponent windowComponent) {
        final TaskFormModal taskFormModal = createTaskFormModal(windowComponent);

        final MessageLoader messageLoader = MessageLoader.getInstance();
        taskFormModal.setModalTitle(messageLoader.getMessage("adding.task.form.modal.title"));
        taskFormModal.setDefaultCloseOperation(ModalWrapperComponent.DISPOSE_ON_CLOSE);
        taskFormModal.setVisible(true);

        return taskFormModal.getFormData();
    }

    public Optional<TaskDto> showUpdatingTaskFormModal(ViewComponent windowComponent, TaskDto taskDto) {
        final TaskFormModal taskFormModal = createTaskFormModal(windowComponent);
        taskFormModal.setFormData(taskDto);

        final MessageLoader messageLoader = MessageLoader.getInstance();
        taskFormModal.setModalTitle(messageLoader.getMessage("updating.task.form.modal.title"));
        taskFormModal.setDefaultCloseOperation(ModalWrapperComponent.DISPOSE_ON_CLOSE);
        taskFormModal.setVisible(true);

        return taskFormModal.getFormData();
    }
}
