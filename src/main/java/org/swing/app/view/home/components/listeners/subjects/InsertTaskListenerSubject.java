package org.swing.app.view.home.components.listeners.subjects;

import org.swing.app.dto.TaskDto;

import java.util.EventObject;
import java.util.Optional;

public interface InsertTaskListenerSubject {

    Optional<TaskDto> getTaskDtoToInsert(EventObject eventObject);

    void onInsertTaskSuccess(EventObject eventObject, TaskDto insertedTaskDto);

    void onInsertTaskFailure(EventObject eventObject);
}
