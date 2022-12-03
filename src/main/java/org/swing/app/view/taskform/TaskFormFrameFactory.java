package org.swing.app.view.taskform;

import org.swing.app.controller.TaskFormFrameController;
import org.swing.app.dto.TaskDto;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.taskform.nodetask.NodeTaskFormFactory;
import org.swing.app.view.taskform.roottask.RootTaskFormFactory;

public class TaskFormFrameFactory {

    public static TaskFormFrame createAddingRootTaskFormFrame(TaskFormFrameController taskFormFrameController) {
        final TaskFormFrame addingRootTaskFormFrame = new TaskFormFrame(
                taskFormFrameController, new RootTaskFormFactory());

        addingRootTaskFormFrame.resize(ViewConstant.ROOT_TASK_FRAME_PREFER_SIZE);
        addingRootTaskFormFrame.setVisible(true);

        return addingRootTaskFormFrame;
    }

    public static TaskFormFrame createUpdatingRootTaskFormFrame(
            TaskFormFrameController taskFormFrameController, TaskDto taskDto) {

        final TaskFormFrame updatingRootTaskFormFrame = new TaskFormFrame(
                taskFormFrameController, new RootTaskFormFactory(), taskDto);

        updatingRootTaskFormFrame.resize(ViewConstant.ROOT_TASK_FRAME_PREFER_SIZE);
        updatingRootTaskFormFrame.setVisible(true);

        return updatingRootTaskFormFrame;
    }

    public static TaskFormFrame createAddingNodeTaskFormFrame(TaskFormFrameController taskFormFrameController) {
        final TaskFormFrame addingNodeTaskFormFrame = new TaskFormFrame(
                taskFormFrameController, new NodeTaskFormFactory());

        addingNodeTaskFormFrame.resize(ViewConstant.NODE_TASK_FRAME_PREFER_SIZE);
        addingNodeTaskFormFrame.setVisible(true);

        return addingNodeTaskFormFrame;
    }

    public static TaskFormFrame createUpdatingNodeTaskFormFrame(
            TaskFormFrameController taskFormFrameController, TaskDto taskDto) {

        final TaskFormFrame updatingNodeTaskFormFrame = new TaskFormFrame(
                taskFormFrameController, new NodeTaskFormFactory(), taskDto);

        updatingNodeTaskFormFrame.resize(ViewConstant.NODE_TASK_FRAME_PREFER_SIZE);
        updatingNodeTaskFormFrame.setVisible(true);

        return updatingNodeTaskFormFrame;
    }
}
