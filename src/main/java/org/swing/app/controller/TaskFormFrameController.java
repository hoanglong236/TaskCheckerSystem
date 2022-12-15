package org.swing.app.controller;

import org.swing.app.business.CommonBusiness;
import org.swing.app.business.TaskFormFrameBusiness;
import org.swing.app.dto.TaskDto;
import org.swing.app.util.MessageLoader;
import org.swing.app.view.taskform.TaskFormFrame;
import org.swing.app.view.taskform.factory.TaskFormFrameFactory;
import org.swing.app.view.taskform.leaftask.factory.LeafTaskFormFrameFactory;
import org.swing.app.view.taskform.nodetask.factory.NodeTaskFormFrameFactory;
import org.swing.app.view.taskform.roottask.factory.RootTaskFormFrameFactory;

public class TaskFormFrameController extends ControllerBase {

    private HomeFrameController homeFrameController;

    private TaskFormFrameBusiness taskFormFrameBusiness;
    private CommonBusiness commonBusiness;

    private TaskFormFrame taskFormFrame;

    public TaskFormFrameController(HomeFrameController homeFrameController) {
        this.homeFrameController = homeFrameController;
        this.taskFormFrame = null;
        this.taskFormFrameBusiness = new TaskFormFrameBusiness();
        this.commonBusiness = new CommonBusiness();
    }

    // TODO: handle this
    public void insertTaskByDto(TaskDto taskDto) {
        final boolean isSuccess = this.commonBusiness.insertTaskByDto(taskDto);
        final MessageLoader messageLoader = MessageLoader.getInstance();
    }

    public void updateTaskByDto(TaskDto taskDto) {
        final boolean isSuccess = this.taskFormFrameBusiness.updateTaskByDto(taskDto);
        this.homeFrameController.handlerForActionUpdateTask(isSuccess, taskDto.getId());
    }

    public void startAddingTaskFormFrame(byte taskType) {
        final TaskFormFrameFactory taskFormFrameFactory = getTaskFormFrameFactoryByTaskType(taskType);
        this.taskFormFrame = taskFormFrameFactory.createAddingTaskFormFrame(this);
        this.taskFormFrame.setVisible(true);
    }

    public void startUpdatingRootTaskFormFrame(byte taskType, String taskId) {
        final TaskDto taskDto = taskFormFrameBusiness.getTaskDtoById(taskId);
        if (taskDto == null) {
            startAddingTaskFormFrame(taskType);
            return;
        }

        final TaskFormFrameFactory taskFormFrameFactory = getTaskFormFrameFactoryByTaskType(taskType);
        this.taskFormFrame = taskFormFrameFactory.createUpdatingTaskFormFrame(this, taskDto);
        this.taskFormFrame.setVisible(true);
    }

    private TaskFormFrameFactory getTaskFormFrameFactoryByTaskType(byte taskType) {
        switch (taskType) {
            case ROOT_TASK_TYPE:
                return new RootTaskFormFrameFactory();
            case NODE_TASK_TYPE:
                return new NodeTaskFormFrameFactory();
            case LEAF_TASK_TYPE:
                return new LeafTaskFormFrameFactory();
            default:
                throw new IllegalArgumentException();
        }
    }
}
