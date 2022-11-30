package org.swing.app.dao;

import org.swing.app.common.RandomString;
import org.swing.app.dto.TaskDto;

import java.sql.Connection;

public class EditTaskFrameDaoImpl implements EditTaskFrameDao {

    private static final Connection CONNECTION = MySQLConnection.getConnection();

    @Override
    public boolean insertTaskByDto(TaskDto taskDto) {
        return false;
    }

    @Override
    public boolean updateTaskByDto(TaskDto taskDto) {
        return false;
    }

    @Override
    public String generateTaskId() {
        final byte maxTaskIdLength = 12;
        final RandomString randomString = new RandomString();
        return randomString.generateString(maxTaskIdLength);
    }

    @Override
    public boolean isTaskIdExist(String taskId) {
        return false;
    }
}
