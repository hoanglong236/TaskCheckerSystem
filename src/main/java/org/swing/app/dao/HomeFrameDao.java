package org.swing.app.dao;

import org.swing.app.dao.exception.DaoException;
import org.swing.app.dto.TaskDto;
import org.swing.app.dto.TaskPanelDto;

import java.util.Optional;
import java.util.Set;

public interface HomeFrameDao {

    Set<TaskPanelDto> getIncompleteRootTaskPanelDtos() throws DaoException;

    Set<TaskPanelDto> getTaskPanelDtosByParentId(String parentId) throws DaoException;

    Optional<TaskPanelDto> getTaskPanelDtoById(String taskId) throws DaoException;

    Optional<TaskDto> getTaskDtoById(String taskId) throws DaoException;

    void insertTaskByDto(TaskDto taskDto) throws DaoException;

    void updateTaskByDto(TaskDto taskDto) throws DaoException;

    void deleteTaskById(String taskId) throws DaoException;
}
