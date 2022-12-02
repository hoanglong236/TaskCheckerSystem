package org.swing.app.dao;

import org.swing.app.util.RandomString;
import org.swing.app.dto.TaskDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class TaskFormFrameDaoImpl implements TaskFormFrameDao {

    private static final Connection CONNECTION = MySQLConnection.getConnection();

    private String getInsertTaskByDtoQuery() {
        final StringBuilder query = new StringBuilder();

        query.append("INSERT INTO task(id, parent_id, title, important, start_datetime, finish_datetime, note)\n");
        query.append("VALUES(?, ?, ?, ?, ?, ?, ?) \n");

        return query.toString();
    }

    @Override
    public boolean insertTaskByDto(TaskDto taskDto) {
        final String query = getInsertTaskByDtoQuery();

        try {
            final PreparedStatement preStmt = CONNECTION.prepareStatement(query);

            preStmt.setString(1, taskDto.getId());
            preStmt.setString(2, taskDto.getParentId());
            preStmt.setString(3, taskDto.getTitle());
            preStmt.setBoolean(4, taskDto.isImportant());
            preStmt.setTimestamp(5, Timestamp.valueOf(taskDto.getStartDatetime()));
            preStmt.setTimestamp(6, Timestamp.valueOf(taskDto.getFinishDatetime()));
            preStmt.setString(7, taskDto.getNote());

            preStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    private String getUpdateTaskByDtoQuery() {
        final StringBuilder query = new StringBuilder();

        query.append("UPDATE task\n");
        query.append("SET title = ?\n");
        query.append("    AND important = ?\n");
        query.append("    AND start_datetime = ?\n");
        query.append("    AND finish_datetime = ?\n");
        query.append("    AND note = ?\n");
        query.append("WHERE id = ?");

        return query.toString();
    }

    @Override
    public boolean updateTaskByDto(TaskDto taskDto) {
        final String query = getUpdateTaskByDtoQuery();

        try {
            final PreparedStatement preStmt = CONNECTION.prepareStatement(query);

            preStmt.setString(1, taskDto.getTitle());
            preStmt.setBoolean(2, taskDto.isImportant());
            preStmt.setTimestamp(3, Timestamp.valueOf(taskDto.getStartDatetime()));
            preStmt.setTimestamp(4, Timestamp.valueOf(taskDto.getFinishDatetime()));
            preStmt.setString(5, taskDto.getNote());
            preStmt.setString(6, taskDto.getId());

            preStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public String generateTaskId() {
        final byte maxTaskIdLength = 12;
        final RandomString randomString = new RandomString();
        return randomString.generateString(maxTaskIdLength);
    }

    @Override
    public boolean isTaskIdExist(String taskId) {
        final String query = "SELECT COUNT(*) AS task_count FROM task WHERE id = ?";

        try {
            final PreparedStatement preStmt = CONNECTION.prepareStatement(query);
            preStmt.setString(1, taskId);
            final ResultSet resultSet = preStmt.executeQuery();

            while(resultSet.next()){
                final int count = resultSet.getInt("task_count");
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    private String getTaskDtoByIdQuery() {
        final StringBuilder query = new StringBuilder();

        query.append("SELECT title, important, start_datetime, finish_datetime, note\n");
        query.append("FROM task\n");
        query.append("WHERE id = ?");

        return query.toString();
    }

    @Override
    public TaskDto getTaskDtoById(String taskId) {
        final String query = getTaskDtoByIdQuery();

        try {
            final PreparedStatement preStmt = CONNECTION.prepareStatement(query);
            preStmt.setString(1, taskId);
            final ResultSet resultSet = preStmt.executeQuery();

            if (resultSet.next()){
                final TaskDto taskDto = new TaskDto();
                taskDto.setId(taskId);
                taskDto.setTitle(resultSet.getString("title"));
                taskDto.setImportant(resultSet.getBoolean("important"));
                taskDto.setStartDatetime(resultSet.getTimestamp("start_datetime").toLocalDateTime());
                taskDto.setFinishDatetime(resultSet.getTimestamp("finish_datetime").toLocalDateTime());
                taskDto.setNote(resultSet.getString("note"));
                return taskDto;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
