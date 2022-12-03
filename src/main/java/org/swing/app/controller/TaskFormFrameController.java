package org.swing.app.controller;

import org.swing.app.business.TaskFormFrameBusiness;
import org.swing.app.dto.TaskDto;
import org.swing.app.util.MessageLoader;
import org.swing.app.view.taskform.TaskFormFrame;
import org.swing.app.view.taskform.TaskFormFrameFactory;

public class TaskFormFrameController {

    private TaskFormFrame taskFormFrame;
    private TaskFormFrameBusiness taskFormFrameBusiness;

    public TaskFormFrameController() {
        this.taskFormFrame = null;
        this.taskFormFrameBusiness = new TaskFormFrameBusiness();
    }

    public void insertTaskByDto(TaskDto taskDto) {
        final boolean isSuccess = this.taskFormFrameBusiness.insertTaskByDto(taskDto);
        final MessageLoader messageLoader = MessageLoader.getInstance();
        if (isSuccess) {
            this.taskFormFrame.showMessageDialog(messageLoader.getMessage("insert.success"));
        } else {
            this.taskFormFrame.showMessageDialog(messageLoader.getMessage("insert.failure"));
        }
    }

    public void updateTaskByDto(TaskDto taskDto) {
        final boolean isSuccess = this.taskFormFrameBusiness.updateTaskByDto(taskDto);
        final MessageLoader messageLoader = MessageLoader.getInstance();
        if (isSuccess) {
            this.taskFormFrame.showMessageDialog(messageLoader.getMessage("update.success"));
        } else {
            this.taskFormFrame.showMessageDialog(messageLoader.getMessage("update.failure"));
        }
    }

    public void startAddingRootTaskFormFrame() {
        this.taskFormFrame = TaskFormFrameFactory.createAddingRootTaskFormFrame(this);
    }

    public void startUpdatingRootTaskFormFrame(String taskId) {
        final TaskDto taskDto = taskFormFrameBusiness.getTaskDtoById(taskId);
        if (taskDto == null) {
            startAddingRootTaskFormFrame();
            return;
        }
        this.taskFormFrame = TaskFormFrameFactory.createUpdatingRootTaskFormFrame(this, taskDto);
    }

    public void startAddingNodeTaskFormFrame() {
        this.taskFormFrame = TaskFormFrameFactory.createAddingNodeTaskFormFrame(this);
    }

    public void startUpdatingNodeTaskFormFrame(String taskId) {
        final TaskDto taskDto = taskFormFrameBusiness.getTaskDtoById(taskId);
        if (taskDto == null) {
            startAddingNodeTaskFormFrame();
            return;
        }
        this.taskFormFrame = TaskFormFrameFactory.createUpdatingNodeTaskFormFrame(this, taskDto);
    }
}
