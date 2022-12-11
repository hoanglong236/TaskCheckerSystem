package org.swing.app.controller;

import org.swing.app.business.CommonBusiness;
import org.swing.app.business.HomeFrameBusiness;
import org.swing.app.dto.TaskDto;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.util.MessageLoader;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.home.HomeFrame;
import org.swing.app.view.home.components.taskbase.TaskPanel;

import java.util.Map;
import java.util.Set;

public class HomeFrameController {

    private HomeFrame homeFrame;
    private Map<String, TaskPanel> taskPanelToUpdateMap;
    private final TaskFormFrameController taskFormFrameController;

    private final HomeFrameBusiness homeFrameBusiness;
    private final CommonBusiness commonBusiness;

    public HomeFrameController() {
        this.taskFormFrameController = new TaskFormFrameController(this);
        this.homeFrameBusiness = new HomeFrameBusiness();
        this.commonBusiness = new CommonBusiness();
    }

    public void insertTaskByDto(TaskDto taskDto) {
        final boolean isSuccess = this.commonBusiness.insertTaskByDto(taskDto);
        final MessageLoader messageLoader = MessageLoader.getInstance();

        if (isSuccess) {
            this.homeFrame.showMessageDialog(messageLoader.getMessage("insert.success"));
        } else {
            this.homeFrame.showMessageDialog(messageLoader.getMessage("insert.failure"));
        }
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

    public boolean deleteTaskById(String taskId) {
        return this.homeFrameBusiness.deleteTaskById(taskId);
    }

    public TaskPanelDto getDailyTaskPanelDto() {
        return this.homeFrameBusiness.getDailyTaskPanelDto();
    }

    public void updateRootTaskPanel(TaskPanel taskPanel) {
        final String taskId = taskPanel.getTaskId();

        this.taskPanelToUpdateMap.put(taskId, taskPanel);
        this.taskFormFrameController.startUpdatingRootTaskFormFrame(taskId);

    }

    public void removeTaskPanel(TaskPanel taskPanel) {

    }

    public void updateTaskSuccess(String taskId) {
        final TaskPanel taskPanel = this.taskPanelToUpdateMap.get(taskId);
        final TaskPanelDto taskPanelDto = this.homeFrameBusiness.getTaskPanelDtoById(taskId);

        taskPanel.update(taskPanelDto);
    }
}
