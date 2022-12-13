package org.swing.app.view.taskform.factory;

import org.swing.app.controller.TaskFormFrameController;
import org.swing.app.dto.TaskDto;
import org.swing.app.view.taskform.TaskFormFrame;

public interface TaskFormFrameFactory {

    TaskFormFrame createAddingTaskFormFrame(TaskFormFrameController taskFormFrameController);
    TaskFormFrame createUpdatingTaskFormFrame(TaskFormFrameController taskFormFrameController, TaskDto taskDto);
}
