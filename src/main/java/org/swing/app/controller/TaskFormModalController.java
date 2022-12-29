package org.swing.app.controller;

import org.swing.app.business.CommonBusiness;
import org.swing.app.business.TaskFormModalBusiness;
import org.swing.app.dto.TaskDto;
import org.swing.app.view.components.FrameWrapperComponent;
import org.swing.app.view.components.modal.ModalWrapperComponent;
import org.swing.app.view.taskform.TaskFormModal;
import org.swing.app.view.taskform.factory.TaskFormModalFactory;
import org.swing.app.view.taskform.leaftask.factory.LeafTaskFormModalFactory;
import org.swing.app.view.taskform.nodetask.factory.NodeTaskFormModalFactory;
import org.swing.app.view.taskform.roottask.factory.RootTaskFormModalFactory;

public class TaskFormModalController extends ControllerBase {

    private HomeFrameController homeFrameController;

    private TaskFormModalBusiness taskFormModalBusiness;
    private CommonBusiness commonBusiness;

    private TaskFormModal taskFormModal;

    public TaskFormModalController(HomeFrameController homeFrameController) {
        this.homeFrameController = homeFrameController;
        this.taskFormModal = null;
        this.taskFormModalBusiness = new TaskFormModalBusiness();
        this.commonBusiness = new CommonBusiness();
    }

    private TaskFormModalFactory getTaskFormModalFactoryByTaskType(byte taskType) {
        switch (taskType) {
            case ROOT_TASK_TYPE:
                return new RootTaskFormModalFactory();
            case NODE_TASK_TYPE:
                return new NodeTaskFormModalFactory();
            case LEAF_TASK_TYPE:
                return new LeafTaskFormModalFactory();
            default:
                throw new IllegalArgumentException();
        }
    }

    public void startAddingTaskFormModal(FrameWrapperComponent parentFrame, byte taskType) {
        final TaskFormModalFactory taskFormModalFactory = getTaskFormModalFactoryByTaskType(taskType);

        this.taskFormModal = taskFormModalFactory.createAddingTaskFormModal(parentFrame, this);
        this.taskFormModal.setDefaultCloseOperation(ModalWrapperComponent.DISPOSE_ON_CLOSE);
        this.taskFormModal.setVisible(true);
    }

    public void startUpdatingRootTaskFormModal(FrameWrapperComponent parentFrame, byte taskType, String taskId) {
        final TaskDto taskDto = this.taskFormModalBusiness.getTaskDtoById(taskId);

        if (taskDto == null) {
            startAddingTaskFormModal(parentFrame, taskType);
            return;
        }

        final TaskFormModalFactory taskFormModalFactory = getTaskFormModalFactoryByTaskType(taskType);

        this.taskFormModal = taskFormModalFactory
                .createUpdatingTaskFormModal(parentFrame, this, taskDto);
        this.taskFormModal.setDefaultCloseOperation(ModalWrapperComponent.DISPOSE_ON_CLOSE);
        this.taskFormModal.setVisible(true);
    }

    public void addNewTaskByDto(TaskDto taskDto) {
        final boolean isSuccess = this.commonBusiness.insertTaskByDto(taskDto);
        this.taskFormModal.dispose();
        this.homeFrameController.handlerForAddNewTaskAction(isSuccess, taskDto.getId());
    }

    public void updateTaskByDto(TaskDto taskDto) {
        final boolean isSuccess = this.taskFormModalBusiness.updateTaskByDto(taskDto);
        this.taskFormModal.dispose();
        this.homeFrameController.handlerForUpdateTaskAction(isSuccess, taskDto.getId());
    }
}
