package org.swing.app.view.taskform.factory;

import org.swing.app.dto.TaskDto;
import org.swing.app.util.MessageLoader;
import org.swing.app.view.components.FrameWrapperComponent;
import org.swing.app.view.components.modal.ModalWrapperComponent;
import org.swing.app.view.taskform.TaskFormModal;

import java.util.Optional;

public abstract class TaskFormModalFactory {

    protected abstract TaskFormModal createAddingTaskFormModal(FrameWrapperComponent parentFrame);

    public Optional<TaskDto> showAddingTaskFormModal(FrameWrapperComponent parentFrame) {
        final TaskFormModal addingTaskFormModal = createAddingTaskFormModal(parentFrame);

        final MessageLoader messageLoader = MessageLoader.getInstance();
        addingTaskFormModal.setModalTitle(messageLoader.getMessage("adding.task.form.modal.title"));
        addingTaskFormModal.setDefaultCloseOperation(ModalWrapperComponent.DISPOSE_ON_CLOSE);
        addingTaskFormModal.setVisible(true);

        return addingTaskFormModal.getFormData();
    }

    protected abstract TaskFormModal createUpdatingTaskFormModal(FrameWrapperComponent parentFrame, TaskDto taskDto);

    public Optional<TaskDto> showUpdatingTaskFormModal(FrameWrapperComponent parentFrame, TaskDto taskDto) {
        final TaskFormModal updatingTaskFormModal = createUpdatingTaskFormModal(parentFrame, taskDto);

        final MessageLoader messageLoader = MessageLoader.getInstance();
        updatingTaskFormModal.setModalTitle(messageLoader.getMessage("updating.task.form.modal.title"));
        updatingTaskFormModal.setDefaultCloseOperation(ModalWrapperComponent.DISPOSE_ON_CLOSE);
        updatingTaskFormModal.setVisible(true);

        return updatingTaskFormModal.getFormData();
    }
}
