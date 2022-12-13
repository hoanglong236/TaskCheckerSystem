package org.swing.app.view.taskform.nodetask.factory;

import org.swing.app.dto.TaskDto;
import org.swing.app.view.taskform.TaskForm;
import org.swing.app.view.taskform.factory.TaskFormFactory;
import org.swing.app.view.taskform.nodetask.NodeTaskForm;

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
