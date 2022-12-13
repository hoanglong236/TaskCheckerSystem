package org.swing.app.view.taskform.nodetask;

import org.swing.app.controller.TaskFormFrameController;
import org.swing.app.dto.TaskDto;
import org.swing.app.view.taskform.TaskFormFrame;
import org.swing.app.view.taskform.nodetask.factory.NodeTaskFormFactory;

public class NodeTaskFormFrame extends TaskFormFrame {

    public NodeTaskFormFrame(TaskFormFrameController taskFormFrameController) {
        super(taskFormFrameController, new NodeTaskFormFactory());
    }

    public NodeTaskFormFrame(TaskFormFrameController taskFormFrameController, TaskDto taskDto) {
        super(taskFormFrameController, new NodeTaskFormFactory(), taskDto);
    }
}