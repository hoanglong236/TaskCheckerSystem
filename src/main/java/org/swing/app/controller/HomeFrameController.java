package org.swing.app.controller;

import org.swing.app.business.CommonBusiness;
import org.swing.app.business.HomeFrameBusiness;
import org.swing.app.business.exception.BusinessException;
import org.swing.app.dto.TaskDto;
import org.swing.app.dto.TaskPanelDto;
import org.swing.app.util.MessageLoader;
import org.swing.app.view.common.ComponentSizeConstants;
import org.swing.app.view.components.FrameWrapperComponent;
import org.swing.app.view.home.HomeFrame;

import java.util.LinkedHashSet;
import java.util.Set;

public class HomeFrameController {

    private HomeFrame homeFrame;

    private final HomeFrameBusiness homeFrameBusiness;

    private final CommonBusiness commonBusiness;

    public HomeFrameController() {
        try {
            this.homeFrameBusiness = new HomeFrameBusiness();
            this.commonBusiness = new CommonBusiness();
        } catch (BusinessException e) {
            throw new RuntimeException(e);
        }
    }

    public void startHomeFrame() {
        Set<TaskPanelDto> rootTaskPanelDtos = new LinkedHashSet<>();

        try {
            rootTaskPanelDtos = this.homeFrameBusiness.getIncompleteRootTaskPanelDtos();
        } catch (BusinessException e) {
        }

        this.homeFrame = new HomeFrame(this, rootTaskPanelDtos);
        this.homeFrame.resize(ComponentSizeConstants.HOME_FRAME_PREFER_SIZE);
        this.homeFrame.setDefaultCloseOperation(FrameWrapperComponent.EXIT_ON_CLOSE);
        this.homeFrame.setVisible(true);
    }

    public ControllerResponse requestInsertTask(TaskDto taskDto) {
        final ControllerResponse controllerResponse = new ControllerResponse();
        final MessageLoader messageLoader = MessageLoader.getInstance();

        if (taskDto == null) {
            controllerResponse.setResponseType(ControllerResponse.RESPONSE_TYPE_ERROR);
            return controllerResponse;
        }

        try {
            final String taskId = this.commonBusiness.generateTaskId();
            taskDto.setId(taskId);

            this.homeFrameBusiness.insertTaskByDto(taskDto);
            final TaskDto insertedTaskDto = this.homeFrameBusiness.getTaskDtoById(taskId);

            controllerResponse.putData(messageLoader.getMessage("inserted.task.dto"), insertedTaskDto);
            controllerResponse.setResponseType(ControllerResponse.RESPONSE_TYPE_SUCCESS);
        } catch (BusinessException e) {
            controllerResponse.setResponseType(ControllerResponse.RESPONSE_TYPE_ERROR);
        }

        return controllerResponse;
    }

    public ControllerResponse requestUpdateTask(TaskDto taskDto) {
        final ControllerResponse controllerResponse = new ControllerResponse();
        final MessageLoader messageLoader = MessageLoader.getInstance();

        if (taskDto == null) {
            controllerResponse.setResponseType(ControllerResponse.RESPONSE_TYPE_ERROR);
            return controllerResponse;
        }

        try {
            this.homeFrameBusiness.updateTaskByDto(taskDto);
            final TaskDto updatedTaskDto = this.homeFrameBusiness.getTaskDtoById(taskDto.getId());

            controllerResponse.putData(messageLoader.getMessage("updated.task.dto"), updatedTaskDto);
            controllerResponse.setResponseType(ControllerResponse.RESPONSE_TYPE_SUCCESS);
        } catch (BusinessException e) {
            controllerResponse.setResponseType(ControllerResponse.RESPONSE_TYPE_ERROR);
        }

        return controllerResponse;
    }

    public ControllerResponse requestDeleteTask(String taskId) {
        final ControllerResponse controllerResponse = new ControllerResponse();

        if (taskId == null) {
            controllerResponse.setResponseType(ControllerResponse.RESPONSE_TYPE_ERROR);
            return controllerResponse;
        }

        try {
            this.homeFrameBusiness.deleteTaskById(taskId);
            controllerResponse.setResponseType(ControllerResponse.RESPONSE_TYPE_SUCCESS);
        } catch (BusinessException e) {
            controllerResponse.setResponseType(ControllerResponse.RESPONSE_TYPE_ERROR);
        }

        return controllerResponse;
    }

    public ControllerResponse requestLoadTaskContent(String taskId) {
        final ControllerResponse controllerResponse = new ControllerResponse();
        final MessageLoader messageLoader = MessageLoader.getInstance();

        if (taskId == null) {
            controllerResponse.setResponseType(ControllerResponse.RESPONSE_TYPE_ERROR);
            return controllerResponse;
        }

        try {
            final TaskPanelDto masterTaskPanelDto = this.homeFrameBusiness.getTaskPanelDtoById(taskId);
            controllerResponse.putData(
                    messageLoader.getMessage("master.task.panel.dto"), masterTaskPanelDto);

            if (masterTaskPanelDto != null) {
                final Set<TaskPanelDto> childTaskPanelDtos = this.homeFrameBusiness.getTaskPanelDtosByParentId(taskId);
                controllerResponse.putData(
                        messageLoader.getMessage("child.task.panel.dtos"), childTaskPanelDtos);
            }

            controllerResponse.setResponseType(ControllerResponse.RESPONSE_TYPE_SUCCESS);
        } catch (BusinessException e) {
            controllerResponse.setResponseType(ControllerResponse.RESPONSE_TYPE_ERROR);
        }

        return controllerResponse;
    }
}
