package org.swing.app.business;

import org.swing.app.dao.HomeFrameDao;
import org.swing.app.dao.impl.HomeFrameDaoImpl;
import org.swing.app.dto.TaskDto;
import org.swing.app.dto.TaskPanelDto;

import java.util.Optional;
import java.util.Set;

public class HomeFrameBusiness {

    private static final HomeFrameDao HOME_FRAME_DAO = new HomeFrameDaoImpl();

    public Set<TaskPanelDto> getIncompleteRootTaskPanelDtos() {
        return HOME_FRAME_DAO.getIncompleteRootTaskPanelDtos();
    }

    public Set<TaskPanelDto> getTaskPanelDtosByParentId(String parentId) {
        return HOME_FRAME_DAO.getTaskPanelDtosByParentId(parentId);
    }

    public Optional<TaskPanelDto> getTaskPanelDtoById(String taskId) {
        return HOME_FRAME_DAO.getTaskPanelDtoById(taskId);
    }

    public Optional<TaskDto> getTaskDtoById(String taskId) {
        return HOME_FRAME_DAO.getTaskDtoById(taskId);
    }

    public boolean insertTaskByDto(TaskDto taskDto) {
        return HOME_FRAME_DAO.insertTaskByDto(taskDto);
    }

    public boolean updateTaskByDto(TaskDto taskDto) {
        return HOME_FRAME_DAO.updateTaskByDto(taskDto);
    }

    public boolean deleteTaskById(String taskId) {
        return HOME_FRAME_DAO.deleteTaskById(taskId);
    }
}
