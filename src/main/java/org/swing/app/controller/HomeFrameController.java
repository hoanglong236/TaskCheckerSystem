package org.swing.app.controller;

import org.swing.app.business.CommonBusiness;
import org.swing.app.business.HomeFrameBusiness;
import org.swing.app.dto.TaskDto;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.util.MessageLoader;
import org.swing.app.view.common.ViewConstant;
import org.swing.app.view.home.HomeFrame;

import java.util.Set;

public class HomeFrameController {

    private HomeFrame homeFrame;

    private HomeFrameBusiness homeFrameBusiness;
    private CommonBusiness commonBusiness;

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

        this.homeFrame = new HomeFrame(dailyTaskPanelDto, rootTaskPanelDtos);
        this.homeFrame.resize(ViewConstant.HOME_FRAME_PREFER_SIZE);
        this.homeFrame.setVisible(true);
    }

    public Set<TaskPanelDto> getIncompleteRootTaskPanelDtos() {
        return this.homeFrameBusiness.getIncompleteRootTaskPanelDtos();
    }

    public Set<TaskPanelDto> getNodeTaskPanelDtosByParentId(String parentId) {
        return this.homeFrameBusiness.getNodeTaskPanelDtosByParentId(parentId);
    }

    public Set<TaskPanelDto> getLeafTaskPanelDtosByParentId(String parentId) {
        return this.homeFrameBusiness.getLeafTaskPanelDtosByParentId(parentId);
    }

    public boolean deleteTaskById(String taskId) {
        return this.homeFrameBusiness.deleteTaskById(taskId);
    }

    public TaskPanelDto getDailyTaskPanelDto() {
        return this.homeFrameBusiness.getDailyTaskPanelDto();
    }
}
