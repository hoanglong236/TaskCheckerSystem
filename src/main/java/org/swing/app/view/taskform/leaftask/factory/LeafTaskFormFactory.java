package org.swing.app.view.taskform.leaftask.factory;

import org.swing.app.dto.TaskDto;
import org.swing.app.view.taskform.TaskForm;
import org.swing.app.view.taskform.factory.TaskFormFactory;
import org.swing.app.view.taskform.leaftask.LeafTaskForm;

public class LeafTaskFormFactory implements TaskFormFactory {

    @Override
    public TaskForm createTaskForm() {
        return new LeafTaskForm();
    }

    @Override
    public TaskForm createTaskForm(TaskDto taskDto) {
        return new LeafTaskForm(taskDto);
    }
}
