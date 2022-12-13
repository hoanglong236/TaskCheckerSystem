package org.swing.app.view.taskform.roottask;

import org.swing.app.controller.TaskFormFrameController;
import org.swing.app.dto.TaskDto;
import org.swing.app.view.taskform.TaskFormFrame;
import org.swing.app.view.taskform.roottask.factory.RootTaskFormFactory;

public class RootTaskFormFrame extends TaskFormFrame {

    public RootTaskFormFrame(TaskFormFrameController taskFormFrameController) {
        super(taskFormFrameController, new RootTaskFormFactory());
    }

    public RootTaskFormFrame(TaskFormFrameController taskFormFrameController, TaskDto taskDto) {
        super(taskFormFrameController, new RootTaskFormFactory(), taskDto);
    }
}
