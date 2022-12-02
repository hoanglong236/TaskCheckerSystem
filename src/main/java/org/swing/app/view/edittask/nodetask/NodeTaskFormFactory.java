package org.swing.app.view.edittask.nodetask;

import org.swing.app.dto.TaskDto;
import org.swing.app.view.edittask.TaskForm;
import org.swing.app.view.edittask.TaskFormFactory;

public class NodeTaskFormFactory implements TaskFormFactory {

    @Override
    public TaskForm createTaskForm() {
        return new NodeTaskForm();
    }

    @Override
    public TaskForm createTaskForm(TaskDto taskDto) {
        return new NodeTaskForm(taskDto);
    }
}