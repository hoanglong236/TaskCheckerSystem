package org.swing.app.controller;

import org.swing.app.business.CommonBusiness;
import org.swing.app.business.HomeFrameBusiness;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.components.request.LoadableTaskComponent;
import org.swing.app.view.components.request.RequestableComponent;
import org.swing.app.view.components.request.DeletableTaskComponent;
import org.swing.app.view.home.HomeFrame;
import org.swing.app.view.components.request.InsertableTaskComponent;
import org.swing.app.view.components.request.UpdatableTaskComponent;

import java.util.LinkedHashSet;
import java.util.Set;

public class HomeFrameController extends ControllerBase {

    private HomeFrame homeFrame;
    private RequestableComponent requestingComponent;

    private final TaskFormModalController taskFormModalController;

    private final HomeFrameBusiness homeFrameBusiness;
    private final CommonBusiness commonBusiness;

    public HomeFrameController() {
        this.taskFormModalController = new TaskFormModalController(this);
        this.homeFrameBusiness = new HomeFrameBusiness();
        this.commonBusiness = new CommonBusiness();
    }

    // TODO: handle this
    public void startHomeFrame() {
        final Set<TaskPanelDto> staticTaskPanelDtos = new LinkedHashSet<>();
        final Set<TaskPanelDto> dynamicTaskPanelDtos = this.homeFrameBusiness.getIncompleteRootTaskPanelDtos();

        this.homeFrame = new HomeFrame(this, staticTaskPanelDtos, dynamicTaskPanelDtos);
        this.homeFrame.resize(ViewConstant.HOME_FRAME_PREFER_SIZE);
        this.homeFrame.setVisible(true);
    }

    public boolean hasRequestingComponent() {
        return this.requestingComponent != null;
    }

    public void requestLoadTaskContent(LoadableTaskComponent loadableTaskComponent,
            byte taskType, String taskId) {

        if (taskId == null) {
            throw new IllegalArgumentException();
        }
        if (taskType == ROOT_TASK_TYPE || taskType == NODE_TASK_TYPE) {
            this.requestingComponent = loadableTaskComponent;
            handlerForLoadContentTaskAction(taskType, taskId);
        }
        if (taskType == LEAF_TASK_TYPE) {
            return;
        }
        throw new IllegalArgumentException();
    }

    private void handlerForLoadContentTaskAction(byte taskType, String taskId) {
        if (this.requestingComponent instanceof LoadableTaskComponent) {
            final TaskPanelDto taskPanelDto = this.homeFrameBusiness.getTaskPanelDtoById(taskId);
            final Set<TaskPanelDto> childTaskPanelDtos = this.homeFrameBusiness.getTaskPanelDtosByParentId(taskId);

            if (taskType == ROOT_TASK_TYPE) {
                this.homeFrame.loadRootTaskContentPanel(taskPanelDto.getTitle(), childTaskPanelDtos);
            } else if (taskType == NODE_TASK_TYPE) {
                this.homeFrame.loadNodeTaskContentPanel(taskPanelDto.getTitle(), childTaskPanelDtos);
            }

            ((LoadableTaskComponent) this.requestingComponent).handlerForResultOfLoadTaskAction();
            this.requestingComponent = null;
        }
        throw new UnsupportedOperationException();
    }

    public void requestAddNewTaskPanel(InsertableTaskComponent insertableTaskComponent, byte taskType) {
        this.requestingComponent = insertableTaskComponent;
        this.taskFormModalController.startAddingTaskFormModal(this.homeFrame, taskType);
    }

    public void handlerForAddNewTaskAction(boolean isSuccess, String taskId) {
        if (this.requestingComponent instanceof InsertableTaskComponent) {
            final TaskPanelDto taskPanelDto = this.homeFrameBusiness.getTaskPanelDtoById(taskId);
            ((InsertableTaskComponent) this.requestingComponent)
                    .handlerForResultOfInsertTaskAction(isSuccess, taskPanelDto);

            this.requestingComponent = null;
            return;
        }
        throw new UnsupportedOperationException();
    }

    public void requestUpdateTaskPanel(UpdatableTaskComponent updatableTaskComponent,
            byte taskType, String taskId) {

        this.requestingComponent = updatableTaskComponent;
        this.taskFormModalController.startUpdatingRootTaskFormModal(this.homeFrame, taskType, taskId);
    }

    public void handlerForUpdateTaskAction(boolean isSuccess, String taskId) {
        if (this.requestingComponent instanceof UpdatableTaskComponent) {
            final TaskPanelDto taskPanelDto = this.homeFrameBusiness.getTaskPanelDtoById(taskId);
            ((UpdatableTaskComponent) this.requestingComponent)
                    .handlerForResultOfUpdateTaskAction(isSuccess, taskPanelDto);

            this.requestingComponent = null;
            return;
        }
        throw new UnsupportedOperationException();
    }

    public void requestDeleteTaskPanel(DeletableTaskComponent deletableTaskComponent, String taskId) {
        this.requestingComponent = deletableTaskComponent;
        handlerForDeleteTaskAction(taskId);
    }

    private void handlerForDeleteTaskAction(String taskId) {
        if (this.requestingComponent instanceof DeletableTaskComponent) {
            final DeletableTaskComponent deletableTaskComponent = (DeletableTaskComponent) this.requestingComponent;
            final boolean isConfirm = deletableTaskComponent.showDeleteTaskConfirmDialog();

            if (isConfirm) {
                final boolean isSuccess = this.homeFrameBusiness.deleteTaskById(taskId);
                deletableTaskComponent.handlerForResultOfDeleteTaskAction(isSuccess);
            } else {
                deletableTaskComponent.handlerForCancelDeleteTaskAction();
            }

            this.requestingComponent = null;
            return;
        }
        throw new UnsupportedOperationException();
    }
}
