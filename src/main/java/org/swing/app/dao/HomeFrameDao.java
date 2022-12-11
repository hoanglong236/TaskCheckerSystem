package org.swing.app.dao;

import org.swing.app.dto.TaskPanelDto;

import java.util.Set;

public interface HomeFrameDao {

    Set<TaskPanelDto> getIncompleteRootTaskPanelDtos();
    Set<TaskPanelDto> getTaskPanelDtosByParentId(String parentId);

    TaskPanelDto getTaskPanelDtoById(String taskId);

    boolean deleteTaskById(String taskId);
}
