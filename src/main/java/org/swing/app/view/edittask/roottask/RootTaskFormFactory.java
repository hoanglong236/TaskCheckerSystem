package org.swing.app.view.edittask.roottask;

import org.swing.app.dto.TaskDto;
import org.swing.app.view.edittask.TaskFormBase;
import org.swing.app.view.edittask.TaskFormFactory;

public class RootTaskFormFactory implements TaskFormFactory {

    @Override
    public TaskFormBase createTaskForm() {
        return new RootTaskForm();
    }

    @Override
    public TaskFormBase createTaskForm(TaskDto taskDto) {
        return new RootTaskForm(taskDto);
    }
}