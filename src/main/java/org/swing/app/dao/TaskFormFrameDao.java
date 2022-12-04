package org.swing.app.dao;

import org.swing.app.dto.TaskDto;

public interface TaskFormFrameDao {

    boolean updateTaskByDto(TaskDto taskDto);
    String generateTaskId();
    boolean isTaskIdExist(String taskId);
    TaskDto getTaskDtoById(String taskId);
}
