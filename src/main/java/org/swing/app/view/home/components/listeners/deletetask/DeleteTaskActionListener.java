package org.swing.app.view.home.components.listeners.deletetask;

import org.swing.app.controller.ControllerResponse;
import org.swing.app.controller.HomeFrameController;
import org.swing.app.view.home.components.listeners.deletetask.DeleteTaskListenerSubject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;
import java.util.Optional;

public class DeleteTaskActionListener implements ActionListener {

    private final HomeFrameController homeFrameController;
    private final DeleteTaskListenerSubject deleteTaskListenerSubject;

    public DeleteTaskActionListener(HomeFrameController homeFrameController,
            DeleteTaskListenerSubject deleteTaskListenerSubject) {

        this.homeFrameController = homeFrameController;
        this.deleteTaskListenerSubject = deleteTaskListenerSubject;
    }

    private void handleDeleteTaskResponse(EventObject eventObject, ControllerResponse response) {
        if (response.getResponseType() == ControllerResponse.RESPONSE_TYPE_SUCCESS) {
            this.deleteTaskListenerSubject.onDeleteTaskSuccess(eventObject);
            return;
        }
        if (response.getResponseType() == ControllerResponse.RESPONSE_TYPE_ERROR) {
            this.deleteTaskListenerSubject.onDeleteTaskFailure(eventObject);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final Optional<String> taskIdToDeleteOptional = this.deleteTaskListenerSubject.getTaskIdToDelete(e);
        if (!taskIdToDeleteOptional.isPresent()) {
            return;
        }

        final String taskIdToDelete = taskIdToDeleteOptional.get();
        final ControllerResponse response = this.homeFrameController.requestDeleteTask(taskIdToDelete);
        handleDeleteTaskResponse(e, response);
    }
}
