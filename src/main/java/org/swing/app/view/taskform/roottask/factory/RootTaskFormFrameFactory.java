package org.swing.app.view.taskform.roottask.factory;

import org.swing.app.controller.TaskFormFrameController;
import org.swing.app.dto.TaskDto;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.taskform.TaskFormFrame;
import org.swing.app.view.taskform.factory.TaskFormFrameFactory;
import org.swing.app.view.taskform.roottask.RootTaskFormFrame;

public class RootTaskFormFrameFactory implements TaskFormFrameFactory {

    @Override
    public TaskFormFrame createAddingTaskFormFrame(TaskFormFrameController taskFormFrameController) {
        final TaskFormFrame addingTaskFormFrame = new RootTaskFormFrame(taskFormFrameController);
        addingTaskFormFrame.resize(ViewConstant.ROOT_TASK_FORM_FRAME_PREFER_SIZE);
        return addingTaskFormFrame;
    }

    @Override
    public TaskFormFrame createUpdatingTaskFormFrame(
            TaskFormFrameController taskFormFrameController, TaskDto taskDto) {

        final TaskFormFrame updatingTaskFormFrame = new RootTaskFormFrame(taskFormFrameController, taskDto);
        updatingTaskFormFrame.resize(ViewConstant.ROOT_TASK_FORM_FRAME_PREFER_SIZE);
        return updatingTaskFormFrame;
    }
}
