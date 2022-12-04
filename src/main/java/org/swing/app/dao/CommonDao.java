package org.swing.app.dao;

import org.swing.app.dto.TaskDto;

public interface CommonDao {

    boolean insertTaskByDto(TaskDto taskDto);
}
