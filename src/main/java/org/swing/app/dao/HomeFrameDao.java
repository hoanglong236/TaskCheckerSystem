package org.swing.app.dao;

import org.swing.app.dto.TaskDto;
import org.swing.app.dto.TaskPanelDto;

import java.util.Optional;
import java.util.Set;

public interface HomeFrameDao {

    Set<TaskPanelDto> getIncompleteRootTaskPanelDtos();

    Set<TaskPanelDto> getTaskPanelDtosByParentId(String parentId);

    Optional<TaskPanelDto> getTaskPanelDtoById(String taskId);

    Optional<TaskDto> getTaskDtoById(String taskId);

    boolean insertTaskByDto(TaskDto taskDto);

    boolean updateTaskByDto(TaskDto taskDto);

    boolean deleteTaskById(String taskId);
}
