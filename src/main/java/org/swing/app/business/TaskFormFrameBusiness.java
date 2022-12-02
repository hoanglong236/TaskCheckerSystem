package org.swing.app.business;

import org.swing.app.dao.TaskFormFrameDao;
import org.swing.app.dao.TaskFormFrameDaoImpl;
import org.swing.app.dto.TaskDto;

public class TaskFormFrameBusiness {

    private static final TaskFormFrameDao TASK_FORM_FRAME_DAO = new TaskFormFrameDaoImpl();

    public boolean insertTaskByDto(TaskDto taskDto) {
        return TASK_FORM_FRAME_DAO.insertTaskByDto(taskDto);
    }

    public boolean updateTaskByDto(TaskDto taskDto) {
        return TASK_FORM_FRAME_DAO.updateTaskByDto(taskDto);
    }

    public TaskDto getTaskDtoById(String taskId) {
        return TASK_FORM_FRAME_DAO.getTaskDtoById(taskId);
    }
}
