package org.swing.app.controller;

import org.swing.app.business.CommonBusiness;
import org.swing.app.business.HomeFrameBusiness;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.home.AbleToRequestComponent;
import org.swing.app.view.home.DeletableTaskComponent;
import org.swing.app.view.home.HomeFrame;
import org.swing.app.view.home.InsertableTaskComponent;
import org.swing.app.view.home.UpdatableTaskComponent;

import java.util.Set;

public class HomeFrameController extends ControllerBase {

    private HomeFrame homeFrame;
    private AbleToRequestComponent requestingComponent;
    private final TaskFormFrameController taskFormFrameController;

    private final HomeFrameBusiness homeFrameBusiness;
    private final CommonBusiness commonBusiness;

    public HomeFrameController() {
        this.taskFormFrameController = new TaskFormFrameController(this);
        this.homeFrameBusiness = new HomeFrameBusiness();
        this.commonBusiness = new CommonBusiness();
    }

    public void startHomeFrame() {
        final TaskPanelDto dailyTaskPanelDto = getDailyTaskPanelDto();
        final Set<TaskPanelDto> taskPanelDtos = this.homeFrameBusiness.getIncompleteRootTaskPanelDtos();

        this.homeFrame = new HomeFrame(this, dailyTaskPanelDto, taskPanelDtos);
        this.homeFrame.resize(ViewConstant.HOME_FRAME_PREFER_SIZE);
        this.homeFrame.setVisible(true);
    }

    public Set<TaskPanelDto> getTaskPanelDtosByParentId(String parentId) {
        return this.homeFrameBusiness.getTaskPanelDtosByParentId(parentId);
    }

    public TaskPanelDto getDailyTaskPanelDto() {
        return this.homeFrameBusiness.getDailyTaskPanelDto();
    }

    public boolean hasRequestingComponent() {
        return this.requestingComponent != null;
    }

    public boolean requestAddNewTaskPanel(byte taskType, InsertableTaskComponent insertableTaskComponent) {
        if (hasRequestingComponent()) {
            return false;
        }

        this.requestingComponent = insertableTaskComponent;
        this.taskFormFrameController.startAddingTaskFormFrame(taskType);
        return true;
    }

    public boolean requestUpdateTaskPanel(byte taskType, UpdatableTaskComponent updatableTaskComponent) {
        if (hasRequestingComponent()) {
            return false;
        }

        this.requestingComponent = updatableTaskComponent;
        final String taskId = updatableTaskComponent.getTaskId();
        this.taskFormFrameController.startUpdatingRootTaskFormFrame(taskType, taskId);
        return true;
    }

    public boolean requestDeleteTaskPanel(DeletableTaskComponent deletableTaskComponent) {
        if (hasRequestingComponent()) {
            return false;
        }

        this.requestingComponent = deletableTaskComponent;
        final String taskId = deletableTaskComponent.getTaskId();
        final boolean isSuccess = this.homeFrameBusiness.deleteTaskById(taskId);
        handlerForActionDeleteTask(isSuccess);
        return true;
    }

    public boolean requestLoadTaskContent(byte taskType, String taskId) {
        if (hasRequestingComponent()) {
            return false;
        }
        if (taskId == null) {
            throw new IllegalArgumentException();
        }
        if (taskType == ROOT_TASK_TYPE || taskType == NODE_TASK_TYPE) {
            handlerForActionLoadContentTask(taskType, taskId);
            return true;
        }
        if (taskType == LEAF_TASK_TYPE) {
            return true;
        }
        throw new IllegalArgumentException();
    }

    public void handlerForActionAddNewTask(boolean isSuccess, String taskId) {
        if (this.requestingComponent instanceof InsertableTaskComponent) {
            final TaskPanelDto taskPanelDto = this.homeFrameBusiness.getTaskPanelDtoById(taskId);
            ((InsertableTaskComponent) this.requestingComponent)
                    .handlerForResultOfInsertTaskAction(isSuccess, taskPanelDto);
            this.requestingComponent = null;
            return;
        }
        throw new UnsupportedOperationException();
    }

    public void handlerForActionUpdateTask(boolean isSuccess, String taskId) {
        if (this.requestingComponent instanceof UpdatableTaskComponent) {
            final TaskPanelDto taskPanelDto = this.homeFrameBusiness.getTaskPanelDtoById(taskId);
            ((UpdatableTaskComponent) this.requestingComponent)
                    .handlerForResultOfUpdateTaskAction(isSuccess, taskPanelDto);
            this.requestingComponent = null;
            return;
        }
        throw new UnsupportedOperationException();
    }

    private void handlerForActionDeleteTask(boolean isSuccess) {
        if (this.requestingComponent instanceof DeletableTaskComponent) {
            ((DeletableTaskComponent) this.requestingComponent).handlerForResultOfDeleteTaskAction(isSuccess);
            this.requestingComponent = null;
            return;
        }
        throw new UnsupportedOperationException();
    }

    private void handlerForActionLoadContentTask(byte taskType, String taskId) {
        final TaskPanelDto taskPanelDto = this.homeFrameBusiness.getTaskPanelDtoById(taskId);
        final Set<TaskPanelDto> childTaskPanelDtos = this.homeFrameBusiness.getTaskPanelDtosByParentId(taskId);

        if (taskType == ROOT_TASK_TYPE) {
            this.homeFrame.loadRootTaskContentPanel(taskPanelDto.getTitle(), childTaskPanelDtos);
        } else if (taskType == NODE_TASK_TYPE) {
            this.homeFrame.loadNodeTaskContentPanel(taskPanelDto.getTitle(), childTaskPanelDtos);
        }
    }
}
