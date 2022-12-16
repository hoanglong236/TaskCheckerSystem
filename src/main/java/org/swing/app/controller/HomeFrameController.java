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
        final Set<TaskPanelDto> rootTaskPanelDtos = getIncompleteRootTaskPanelDtos();

        this.homeFrame = new HomeFrame(this, dailyTaskPanelDto, rootTaskPanelDtos);
        this.homeFrame.resize(ViewConstant.HOME_FRAME_PREFER_SIZE);
        this.homeFrame.setVisible(true);
    }

    public Set<TaskPanelDto> getIncompleteRootTaskPanelDtos() {
        return this.homeFrameBusiness.getIncompleteRootTaskPanelDtos();
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

    public void handlerForActionInsertTask(boolean isSuccess, String taskId) {
        if (this.requestingComponent instanceof InsertableTaskComponent) {
            final TaskPanelDto taskPanelDto = this.homeFrameBusiness.getTaskPanelDtoById(taskId);
            ((InsertableTaskComponent) this.requestingComponent)
                    .handlerForResultOfInsertTaskAction(isSuccess, taskPanelDto);
            this.requestingComponent = null;
            return;
        }
        throw new IllegalCallerException();
    }

    public void handlerForActionUpdateTask(boolean isSuccess, String taskId) {
        if (this.requestingComponent instanceof UpdatableTaskComponent) {
            final TaskPanelDto taskPanelDto = this.homeFrameBusiness.getTaskPanelDtoById(taskId);
            ((UpdatableTaskComponent) this.requestingComponent)
                    .handlerForResultOfUpdateTaskAction(isSuccess, taskPanelDto);
            this.requestingComponent = null;
            return;
        }
        throw new IllegalCallerException();
    }

    private void handlerForActionDeleteTask(boolean isSuccess) {
        if (this.requestingComponent instanceof DeletableTaskComponent) {
            ((DeletableTaskComponent) this.requestingComponent).handlerForResultOfDeleteTaskAction(isSuccess);
            this.requestingComponent = null;
            return;
        }
        throw new IllegalCallerException();
    }
}
