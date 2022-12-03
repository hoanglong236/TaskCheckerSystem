package org.swing.app.view.taskform;

import org.swing.app.dto.TaskDto;

public interface TaskFormFactory {

    TaskForm createTaskForm();
    TaskForm createTaskForm(TaskDto taskDto);
}
