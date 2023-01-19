package org.swing.app.view.home.components.listeners;

import org.swing.app.controller.ControllerResponse;
import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskDto;
import org.swing.app.util.MessageLoader;
import org.swing.app.view.home.components.listeners.subjects.UpdateTaskListenerSubject;

import java.util.EventObject;
import java.util.Optional;

public class UpdateTaskListener {

    protected final HomeFrameController homeFrameController;
    protected final UpdateTaskListenerSubject updateTaskListenerSubject;

    public UpdateTaskListener(HomeFrameController homeFrameController,
            UpdateTaskListenerSubject updateTaskListenerSubject) {

        this.homeFrameController = homeFrameController;
        this.updateTaskListenerSubject = updateTaskListenerSubject;
    }

    private void handleUpdateTaskResponse(EventObject eventObject, ControllerResponse response) {
        final MessageLoader messageLoader = MessageLoader.getInstance();

        if (response.getResponseType() == ControllerResponse.RESPONSE_TYPE_SUCCESS) {
            final Optional<Object> updatedTaskDtoOptional = response.getData(
                    messageLoader.getMessage("updated.task.dto"));

            if (!updatedTaskDtoOptional.isPresent()) {
                this.updateTaskListenerSubject.onUpdateTaskFailure(eventObject);
                return;
            }

            this.updateTaskListenerSubject.onUpdateTaskSuccess(eventObject,
                    (TaskDto) updatedTaskDtoOptional.get());
            return;
        }
        if (response.getResponseType() == ControllerResponse.RESPONSE_TYPE_ERROR) {
            this.updateTaskListenerSubject.onUpdateTaskFailure(eventObject);
        }
    }

    protected void handleUpdateTaskEvent(EventObject eventObject) {
        final Optional<TaskDto> newTaskDtoOptional = this.updateTaskListenerSubject.getTaskDtoToUpdate(eventObject);
        if (!newTaskDtoOptional.isPresent()) {
            return;
        }

        final TaskDto taskDtoToUpdate = newTaskDtoOptional.get();
        final ControllerResponse response = this.homeFrameController.requestUpdateTask(taskDtoToUpdate);
        handleUpdateTaskResponse(eventObject, response);
    }
}
