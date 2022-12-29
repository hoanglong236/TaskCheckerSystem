package org.swing.app.dao;

import org.swing.app.dto.TaskDto;

public interface TaskFormModalDao {

    boolean updateTaskByDto(TaskDto taskDto);
    String generateTaskId();
    boolean isTaskIdExist(String taskId);
    TaskDto getTaskDtoById(String taskId);
}
