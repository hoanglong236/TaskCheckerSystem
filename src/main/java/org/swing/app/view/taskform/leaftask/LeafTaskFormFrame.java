package org.swing.app.view.taskform.leaftask;

import org.swing.app.controller.TaskFormFrameController;
import org.swing.app.dto.TaskDto;
import org.swing.app.view.taskform.TaskFormFrame;
import org.swing.app.view.taskform.factory.TaskFormFactory;
import org.swing.app.view.taskform.leaftask.factory.LeafTaskFormFactory;
import org.swing.app.view.taskform.nodetask.factory.NodeTaskFormFactory;

public class LeafTaskFormFrame extends TaskFormFrame {

    public LeafTaskFormFrame(TaskFormFrameController taskFormFrameController) {
        super(taskFormFrameController, new LeafTaskFormFactory());
    }

    public LeafTaskFormFrame(TaskFormFrameController taskFormFrameController, TaskDto taskDto) {
        super(taskFormFrameController, new LeafTaskFormFactory(), taskDto);
    }
}
