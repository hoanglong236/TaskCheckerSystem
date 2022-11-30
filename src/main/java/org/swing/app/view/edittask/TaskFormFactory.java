package org.swing.app.view.edittask;

import org.swing.app.dto.TaskDto;

public interface TaskFormFactory {

    TaskForm createTaskForm();
    TaskForm createTaskForm(TaskDto taskDto);
}
