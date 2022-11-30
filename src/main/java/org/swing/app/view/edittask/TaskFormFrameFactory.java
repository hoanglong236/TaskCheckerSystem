package org.swing.app.view.edittask;

import org.swing.app.dto.TaskDto;
import org.swing.app.view.edittask.nodetask.NodeTaskFormFactory;
import org.swing.app.view.edittask.roottask.RootTaskFormFactory;

public class TaskFormFrameFactory {

    public static TaskFormFrame createAddingRootTaskForm() {
        return new TaskFormFrame(new RootTaskFormFactory());
    }

    public static TaskFormFrame createUpdatingRootTaskForm(TaskDto taskDto) {
        return new TaskFormFrame(new RootTaskFormFactory(), taskDto);
    }

    public static TaskFormFrame createAddingNodeTaskForm() {
        return new TaskFormFrame(new NodeTaskFormFactory());
    }

    public static TaskFormFrame createAddingNodeTaskForm(TaskDto taskDto) {
        return new TaskFormFrame(new NodeTaskFormFactory(), taskDto);
    }
}
