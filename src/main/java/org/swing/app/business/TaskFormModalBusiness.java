package org.swing.app.business;

import org.swing.app.dao.TaskFormModalDao;
import org.swing.app.dao.impl.TaskFormModalImpl;
import org.swing.app.dto.TaskDto;

public class TaskFormModalBusiness {

    private static final TaskFormModalDao TASK_FORM_MODAL_DAO = new TaskFormModalImpl();

    public boolean updateTaskByDto(TaskDto taskDto) {
        return TASK_FORM_MODAL_DAO.updateTaskByDto(taskDto);
    }

    public TaskDto getTaskDtoById(String taskId) {
        return TASK_FORM_MODAL_DAO.getTaskDtoById(taskId);
    }
}
