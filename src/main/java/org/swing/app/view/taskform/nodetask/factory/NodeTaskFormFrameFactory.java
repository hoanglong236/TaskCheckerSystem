package org.swing.app.view.taskform.nodetask.factory;

import org.swing.app.controller.TaskFormFrameController;
import org.swing.app.dto.TaskDto;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.taskform.TaskFormFrame;
import org.swing.app.view.taskform.factory.TaskFormFrameFactory;
import org.swing.app.view.taskform.nodetask.NodeTaskFormFrame;

public class NodeTaskFormFrameFactory implements TaskFormFrameFactory {

    @Override
    public TaskFormFrame createAddingTaskFormFrame(TaskFormFrameController taskFormFrameController) {
        final TaskFormFrame addingTaskFormFrame = new NodeTaskFormFrame(taskFormFrameController);
        addingTaskFormFrame.resize(ViewConstant.NODE_TASK_FORM_FRAME_PREFER_SIZE);
        return addingTaskFormFrame;
    }

    @Override
    public TaskFormFrame createUpdatingTaskFormFrame(
            TaskFormFrameController taskFormFrameController, TaskDto taskDto) {

        final TaskFormFrame updatingTaskFormFrame = new NodeTaskFormFrame(taskFormFrameController, taskDto);
        updatingTaskFormFrame.resize(ViewConstant.NODE_TASK_FORM_FRAME_PREFER_SIZE);
        return updatingTaskFormFrame;
    }
}
