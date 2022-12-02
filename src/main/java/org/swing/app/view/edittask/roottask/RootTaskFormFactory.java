package org.swing.app.view.edittask.roottask;

import org.swing.app.dto.TaskDto;
import org.swing.app.view.edittask.TaskForm;
import org.swing.app.view.edittask.TaskFormFactory;

public class RootTaskFormFactory implements TaskFormFactory {

    @Override
    public TaskForm createTaskForm() {
        return new RootTaskForm();
    }

    @Override
    public TaskForm createTaskForm(TaskDto taskDto) {
        return new RootTaskForm(taskDto);
    }
}