package org.swing.app.view.taskform.roottask.factory;

import org.swing.app.dto.TaskDto;
import org.swing.app.view.taskform.TaskForm;
import org.swing.app.view.taskform.factory.TaskFormFactory;
import org.swing.app.view.taskform.roottask.RootTaskForm;

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