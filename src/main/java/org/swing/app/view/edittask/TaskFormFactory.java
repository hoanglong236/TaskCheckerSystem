package org.swing.app.view.edittask;

import org.swing.app.dto.TaskDto;

public interface TaskFormFactory {

    TaskFormBase createTaskForm();
    TaskFormBase createTaskForm(TaskDto taskDto);
}
