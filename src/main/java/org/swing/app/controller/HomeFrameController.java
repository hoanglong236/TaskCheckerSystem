package org.swing.app.controller;

import org.swing.app.business.CommonBusiness;
import org.swing.app.business.HomeFrameBusiness;
import org.swing.app.dto.TaskDto;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.util.MessageLoader;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.FrameWrapperComponent;
import org.swing.app.view.components.modal.OptionPane;
import org.swing.app.view.components.request.LoadableTaskComponent;
import org.swing.app.view.components.request.DeletableTaskComponent;
import org.swing.app.view.home.HomeFrame;
import org.swing.app.view.components.request.InsertableTaskComponent;
import org.swing.app.view.components.request.UpdatableTaskComponent;
import org.swing.app.view.taskform.factory.TaskFormModalFactory;
import org.swing.app.view.taskform.leaftask.factory.LeafTaskFormModalFactory;
import org.swing.app.view.taskform.nodetask.factory.NodeTaskFormModalFactory;
import org.swing.app.view.taskform.roottask.factory.RootTaskFormModalFactory;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

public class HomeFrameController extends ControllerBase {

    private HomeFrame homeFrame;

    private final HomeFrameBusiness homeFrameBusiness;
    private final CommonBusiness commonBusiness;

    public HomeFrameController() {
        this.homeFrameBusiness = new HomeFrameBusiness();
        this.commonBusiness = new CommonBusiness();
    }

    // TODO: handle this
    public void startHomeFrame() {
        final Set<TaskPanelDto> staticTaskPanelDtos = new LinkedHashSet<>();
        final Set<TaskPanelDto> dynamicTaskPanelDtos = this.homeFrameBusiness.getIncompleteRootTaskPanelDtos();

        this.homeFrame = new HomeFrame(this, staticTaskPanelDtos, dynamicTaskPanelDtos);
        this.homeFrame.resize(ViewConstant.HOME_FRAME_PREFER_SIZE);
        this.homeFrame.setDefaultCloseOperation(FrameWrapperComponent.EXIT_ON_CLOSE);
        this.homeFrame.setVisible(true);
    }

    public void requestLoadTaskContent(LoadableTaskComponent loadableTaskComponent, byte taskType, String taskId) {
        if (taskType == ROOT_TASK_TYPE || taskType == NODE_TASK_TYPE) {
            final Optional<TaskPanelDto> optionalTaskPanelDto = this.homeFrameBusiness.getTaskPanelDtoById(taskId);
            if (!optionalTaskPanelDto.isPresent()) {
                loadableTaskComponent.handleForFailureLoadTaskAction();
                return;
            }

            final TaskPanelDto taskPanelDto = optionalTaskPanelDto.get();
            final Set<TaskPanelDto> childTaskPanelDtos = this.homeFrameBusiness.getTaskPanelDtosByParentId(taskId);

            if (taskType == ROOT_TASK_TYPE) {
                this.homeFrame.loadRootTaskContentPanel(taskPanelDto.getTitle(), childTaskPanelDtos);
            } else {
                this.homeFrame.loadNodeTaskContentPanel(taskPanelDto.getTitle(), childTaskPanelDtos);
            }

            loadableTaskComponent.handleForSuccessLoadTaskAction();
            return;
        }
        if (taskType == LEAF_TASK_TYPE) {
            return;
        }
        throw new IllegalArgumentException();
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

    public void requestAddNewTaskPanel(InsertableTaskComponent insertableTaskComponent, byte taskType) {
        final TaskFormModalFactory taskFormModalFactory = getTaskFormModalFactoryByTaskType(taskType);
        final Optional<TaskDto> formModalResult = taskFormModalFactory.showAddingTaskFormModal(this.homeFrame);

        if (!formModalResult.isPresent()) {
            insertableTaskComponent.handleForCancelInsertTaskAction();
            return;
        }

        final TaskDto taskDto = formModalResult.get();
        final String taskId = this.commonBusiness.generateTaskId();
        taskDto.setId(taskId);

        final boolean isSuccess = this.homeFrameBusiness.insertTaskByDto(taskDto);
        if (isSuccess) {
            final Optional<TaskPanelDto> optionalTaskPanelDto =
                    this.homeFrameBusiness.getTaskPanelDtoById(taskDto.getId());
            insertableTaskComponent.handleForSuccessInsertTaskAction(optionalTaskPanelDto.get());
        } else {
            insertableTaskComponent.handleForFailureInsertTaskAction();
        }
    }

    public void requestUpdateTaskPanel(UpdatableTaskComponent updatableTaskComponent, byte taskType, String taskId) {
        final TaskFormModalFactory taskFormModalFactory = getTaskFormModalFactoryByTaskType(taskType);
        final Optional<TaskDto> oldOptionalTaskDto = this.homeFrameBusiness.getTaskDtoById(taskId);

        if (!oldOptionalTaskDto.isPresent()) {
            updatableTaskComponent.handleForDeniedUpdateTaskAction();
            return;
        }

        final TaskDto oldTaskDto = oldOptionalTaskDto.get();
        final Optional<TaskDto> newOptionalTaskDto = taskFormModalFactory.showUpdatingTaskFormModal(
                this.homeFrame, oldTaskDto);

        if (!newOptionalTaskDto.isPresent()) {
            updatableTaskComponent.handleForCancelUpdateTaskAction();
            return;
        }

        final TaskDto newTaskDto = newOptionalTaskDto.get();
        if (newTaskDto.equals(oldTaskDto)) {
            updatableTaskComponent.handleForNothingToUpdateTaskAction();
            return;
        }

        final boolean isSuccess = this.homeFrameBusiness.updateTaskByDto(newTaskDto);
        if (isSuccess) {
            final Optional<TaskPanelDto> optionalTaskPanelDto =
                    this.homeFrameBusiness.getTaskPanelDtoById(newTaskDto.getId());
            updatableTaskComponent.handleForSuccessUpdateTaskAction(optionalTaskPanelDto.get());
        } else {
            updatableTaskComponent.handleForFailureUpdateTaskAction();
        }
    }

    public void requestDeleteTaskPanel(DeletableTaskComponent deletableTaskComponent, String taskId) {
        final MessageLoader messageLoader = MessageLoader.getInstance();
        final byte confirmResult = OptionPane.showConfirmDialog(messageLoader.getMessage("confirm.dialog.question"),
                messageLoader.getMessage("confirm.dialog.delete.task.title"));

        if (confirmResult != OptionPane.YES_DIALOG_OPTION) {
            deletableTaskComponent.handleForCancelDeleteTaskAction();
            return;
        }

        final boolean isSuccess = this.homeFrameBusiness.deleteTaskById(taskId);
        if (isSuccess) {
            deletableTaskComponent.handleForSuccessDeleteTaskAction();
        } else {
            deletableTaskComponent.handleForFailureDeleteTaskAction();
        }
    }
}
