package org.swing.app.view.taskform.roottask;

import org.swing.app.dto.TaskDto;
import org.swing.app.view.taskform.TaskForm;
import org.swing.app.view.taskform.TaskFormFactory;

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