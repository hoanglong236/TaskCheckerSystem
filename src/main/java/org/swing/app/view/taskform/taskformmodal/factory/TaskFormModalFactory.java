package org.swing.app.view.taskform.taskformmodal.factory;

import org.swing.app.dto.TaskDto;
import org.swing.app.util.MessageLoader;
import org.swing.app.view.components.FrameWrapperComponent;
import org.swing.app.view.components.modal.ModalWrapperComponent;
import org.swing.app.view.taskform.taskformmodal.TaskFormModal;

import java.util.Optional;

public abstract class TaskFormModalFactory {

    protected abstract TaskFormModal createTaskFormModal(FrameWrapperComponent parentFrame);

    public Optional<TaskDto> showAddingTaskFormModal(FrameWrapperComponent parentFrame) {
        final TaskFormModal taskFormModal = createTaskFormModal(parentFrame);

        final MessageLoader messageLoader = MessageLoader.getInstance();
        taskFormModal.setModalTitle(messageLoader.getMessage("adding.task.form.modal.title"));
        taskFormModal.setDefaultCloseOperation(ModalWrapperComponent.DISPOSE_ON_CLOSE);
        taskFormModal.setVisible(true);

        return taskFormModal.getFormData();
    }

    public Optional<TaskDto> showUpdatingTaskFormModal(FrameWrapperComponent parentFrame, TaskDto taskDto) {
        final TaskFormModal taskFormModal = createTaskFormModal(parentFrame);
        taskFormModal.setFormData(taskDto);

        final MessageLoader messageLoader = MessageLoader.getInstance();
        taskFormModal.setModalTitle(messageLoader.getMessage("updating.task.form.modal.title"));
        taskFormModal.setDefaultCloseOperation(ModalWrapperComponent.DISPOSE_ON_CLOSE);
        taskFormModal.setVisible(true);

        return taskFormModal.getFormData();
    }
}
