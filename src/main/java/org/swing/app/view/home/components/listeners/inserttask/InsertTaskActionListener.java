package org.swing.app.view.home.components.listeners.inserttask;

import org.swing.app.controller.ControllerResponse;
import org.swing.app.controller.HomeFrameController;
import org.swing.app.dto.TaskDto;
import org.swing.app.util.MessageLoader;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;
import java.util.Optional;

public class InsertTaskActionListener implements ActionListener {

    private final HomeFrameController homeFrameController;
    private final InsertTaskListenerSubject insertTaskListenerSubject;

    public InsertTaskActionListener(HomeFrameController homeFrameController,
            InsertTaskListenerSubject insertTaskListenerSubject) {

        this.homeFrameController = homeFrameController;
        this.insertTaskListenerSubject = insertTaskListenerSubject;
    }

    private void handleForInsertTaskResponse(EventObject eventObject, ControllerResponse response) {
        final MessageLoader messageLoader = MessageLoader.getInstance();

        if (response.getResponseType() == ControllerResponse.RESPONSE_TYPE_SUCCESS) {
            final Optional<Object> insertedTaskDtoOptional = response.getData(
                    messageLoader.getMessage("inserted.task.dto"));

            if (!insertedTaskDtoOptional.isPresent()) {
                this.insertTaskListenerSubject.onInsertTaskFailure(eventObject);
                return;
            }

            this.insertTaskListenerSubject.onInsertTaskSuccess(eventObject,
                    (TaskDto) insertedTaskDtoOptional.get());
            return;
        }
        if (response.getResponseType() == ControllerResponse.RESPONSE_TYPE_ERROR) {
            this.insertTaskListenerSubject.onInsertTaskFailure(eventObject);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final Optional<TaskDto> taskDtoToInsertOptional = this.insertTaskListenerSubject.getTaskDtoToInsert(e);
        if (!taskDtoToInsertOptional.isPresent()) {
            return;
        }

        final TaskDto taskDtoToInsert = taskDtoToInsertOptional.get();
        final ControllerResponse response = this.homeFrameController.requestInsertTask(taskDtoToInsert);
        handleForInsertTaskResponse(e, response);
    }
}
