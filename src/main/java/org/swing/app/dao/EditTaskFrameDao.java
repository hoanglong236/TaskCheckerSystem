package org.swing.app.dao;

import org.swing.app.dto.TaskDto;

public interface EditTaskFrameDao {

    boolean insertTaskByDto(TaskDto taskDto);
    boolean updateTaskByDto(TaskDto taskDto);
    String generateTaskId();
    boolean isTaskIdExist(String taskId);
}
