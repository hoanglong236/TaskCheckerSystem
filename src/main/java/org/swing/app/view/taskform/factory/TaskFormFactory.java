package org.swing.app.view.taskform.factory;

import org.swing.app.dto.TaskDto;
import org.swing.app.view.taskform.TaskForm;

public interface TaskFormFactory {

    TaskForm createTaskForm();
    TaskForm createTaskForm(TaskDto taskDto);
}
