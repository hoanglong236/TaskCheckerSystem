package org.swing.app.dao;

import org.swing.app.dao.connection.MySQLConnection;
import org.swing.app.util.RandomString;
import org.swing.app.dto.TaskDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class TaskFormFrameDaoImpl implements TaskFormFrameDao {

    private static final Connection CONNECTION = MySQLConnection.getConnection();

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

            if (resultSet.next()) {
                final int count = resultSet.getInt("task_count");
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    private String getTaskDtoByIdQuery() {
        final StringBuilder query = new StringBuilder();

        query.append("SELECT id, title, important, start_datetime, finish_datetime, note\n");
        query.append("FROM task\n");
        query.append("WHERE id = ?");

        return query.toString();
    }

    private TaskDto getTaskDtoFromResultSet(ResultSet resultSet) throws SQLException {
        final TaskDto taskDto = new TaskDto();

        taskDto.setId(resultSet.getString("id"));
        taskDto.setTitle(resultSet.getString("title"));
        taskDto.setImportant(resultSet.getBoolean("important"));
        taskDto.setStartDatetime(resultSet.getTimestamp("start_datetime").toLocalDateTime());
        taskDto.setFinishDatetime(resultSet.getTimestamp("finish_datetime").toLocalDateTime());
        taskDto.setNote(resultSet.getString("note"));

        return taskDto;
    }

    @Override
    public TaskDto getTaskDtoById(String taskId) {
        final String query = getTaskDtoByIdQuery();

        try {
            final PreparedStatement preStmt = CONNECTION.prepareStatement(query);
            preStmt.setString(1, taskId);
            final ResultSet resultSet = preStmt.executeQuery();

            if (resultSet.next()) {
                return getTaskDtoFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
