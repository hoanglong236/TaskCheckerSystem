package org.swing.app.view.home.components.listeners.subjects;

import org.swing.app.dto.TaskDto;

import java.util.EventObject;
import java.util.Optional;

public interface UpdateTaskListenerSubject {

    Optional<TaskDto> getTaskDtoToUpdate(EventObject eventObject);

    void onUpdateTaskSuccess(EventObject eventObject, TaskDto updatedTaskDto);
    void onUpdateTaskFailure(EventObject eventObject);
}
