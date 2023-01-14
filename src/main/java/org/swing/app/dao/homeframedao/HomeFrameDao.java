package org.swing.app.dao.homeframedao;

import org.swing.app.dao.exception.DaoException;
import org.swing.app.dto.TaskDto;
import org.swing.app.dto.TaskPanelDto;

import java.util.Set;

public interface HomeFrameDao {

    void insertTaskByDto(TaskDto taskDto) throws DaoException;

    void updateTaskByDto(TaskDto taskDto) throws DaoException;

    void deleteTaskById(String taskId) throws DaoException;

    TaskPanelDto getTaskPanelDtoById(String taskId) throws DaoException;

    TaskDto getTaskDtoById(String taskId) throws DaoException;

    Set<TaskPanelDto> getIncompleteRootTaskPanelDtos() throws DaoException;

    Set<TaskPanelDto> getTaskPanelDtosByParentId(String parentId) throws DaoException;
}
