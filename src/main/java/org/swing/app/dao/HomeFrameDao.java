package org.swing.app.dao;

import org.swing.app.dto.TaskPanelDto;

import java.util.Set;

public interface HomeFrameDao {

    Set<TaskPanelDto> getIncompleteRootTaskPanelDtos();
    Set<TaskPanelDto> getNodeTaskPanelDtosByParentId(String parentId);
    Set<TaskPanelDto> getLeafTaskPanelDtosByParentId(String parentId);
}
