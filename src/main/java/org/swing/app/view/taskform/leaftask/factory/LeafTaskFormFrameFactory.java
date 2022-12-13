package org.swing.app.view.taskform.leaftask.factory;

import org.swing.app.controller.TaskFormFrameController;
import org.swing.app.dto.TaskDto;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.taskform.TaskFormFrame;
import org.swing.app.view.taskform.factory.TaskFormFrameFactory;
import org.swing.app.view.taskform.leaftask.LeafTaskFormFrame;

public class LeafTaskFormFrameFactory implements TaskFormFrameFactory {

    @Override
    public TaskFormFrame createAddingTaskFormFrame(TaskFormFrameController taskFormFrameController) {
        throw new UnsupportedOperationException();
    }

    @Override
    public TaskFormFrame createUpdatingTaskFormFrame(TaskFormFrameController taskFormFrameController, TaskDto taskDto) {
        final TaskFormFrame addingTaskFormFrame = new LeafTaskFormFrame(taskFormFrameController, taskDto);
        addingTaskFormFrame.resize(ViewConstant.LEAF_TASK_FORM_FRAME_PREFER_SIZE);
        return addingTaskFormFrame;
    }
}
