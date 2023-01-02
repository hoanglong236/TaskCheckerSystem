package org.swing.app.dao.impl;

import org.swing.app.dao.HomeFrameDao;
import org.swing.app.dao.connection.MySQLConnection;
import org.swing.app.dao.sql.HomeFrameDaoSql;
import org.swing.app.dto.TaskDto;
import org.swing.app.dto.TaskPanelDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

public class HomeFrameDaoImpl implements HomeFrameDao {

    private static final Connection CONNECTION = MySQLConnection.getConnection();

    private TaskDto getTaskDtoFromResultSet(ResultSet resultSet) throws SQLException {
        final TaskDto taskDto = new TaskDto();

        taskDto.setId(resultSet.getString("id"));
        taskDto.setTitle(resultSet.getString("title"));
        taskDto.setImportant(resultSet.getBoolean("important"));
        taskDto.setStartDateTime(resultSet.getTimestamp("start_datetime").toLocalDateTime());
        taskDto.setFinishDateTime(resultSet.getTimestamp("finish_datetime").toLocalDateTime());
        taskDto.setNote(resultSet.getString("note"));

        return taskDto;
    }

    private TaskPanelDto getTaskPanelDtoFromResultSet(ResultSet resultSet) throws SQLException {
        final TaskPanelDto taskPanelDto = new TaskPanelDto();
        final Timestamp startTimestamp = resultSet.getTimestamp("start_datetime");
        final Timestamp finishTimestamp = resultSet.getTimestamp("finish_datetime");
        final Timestamp submitTimestamp = resultSet.getTimestamp("submit_datetime");

        taskPanelDto.setId(resultSet.getString("id"));
        taskPanelDto.setParentId(resultSet.getString("parent_id"));
        taskPanelDto.setTitle(resultSet.getString("title"));

        taskPanelDto.setStartDateTime(startTimestamp == null ? null : startTimestamp.toLocalDateTime());
        taskPanelDto.setFinishDateTime(finishTimestamp == null ? null : finishTimestamp.toLocalDateTime());
        taskPanelDto.setSubmitDateTime(submitTimestamp == null ? null : submitTimestamp.toLocalDateTime());

        taskPanelDto.setCompleted(resultSet.getBoolean("is_completed"));
        taskPanelDto.setNote(resultSet.getString("note"));
        taskPanelDto.setChildTaskCompletedCount(resultSet.getInt("child_task_completed_count"));
        taskPanelDto.setChildTaskCount(resultSet.getInt("child_task_count"));

        return taskPanelDto;
    }

    private Set<TaskPanelDto> getTaskPanelDtosFromResultSet(ResultSet resultSet) throws SQLException {
        final Set<TaskPanelDto> taskPanelDtos = new LinkedHashSet<>();

        while(resultSet.next()){
            final TaskPanelDto taskPanelDto = getTaskPanelDtoFromResultSet(resultSet);
            taskPanelDtos.add(taskPanelDto);
        }

        return taskPanelDtos;
    }

    @Override
    public Set<TaskPanelDto> getIncompleteRootTaskPanelDtos() {
        final String sql = HomeFrameDaoSql.createSqlToGetIncompleteRootTaskPanelDto();

        try {
            final PreparedStatement preStmt = CONNECTION.prepareStatement(sql);
            final ResultSet resultSet = preStmt.executeQuery();
            return getTaskPanelDtosFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new LinkedHashSet<>();
    }

    @Override
    public Set<TaskPanelDto> getTaskPanelDtosByParentId(String parentId) {
        final String sql = HomeFrameDaoSql.createSqlToGetTaskPanelDtoByParentId();

        try {
            final PreparedStatement preStmt = CONNECTION.prepareStatement(sql);
            preStmt.setString(1, parentId);

            final ResultSet resultSet = preStmt.executeQuery();
            return getTaskPanelDtosFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new LinkedHashSet<>();
    }

    @Override
    public Optional<TaskPanelDto> getTaskPanelDtoById(String taskId) {
        final String sql = HomeFrameDaoSql.createSqlToGetTaskPanelDtoById();

        try {
            final PreparedStatement preStmt = CONNECTION.prepareStatement(sql);
            preStmt.setString(1, taskId);

            final ResultSet resultSet = preStmt.executeQuery();
            if (resultSet.next()) {
                final TaskPanelDto taskPanelDto = getTaskPanelDtoFromResultSet(resultSet);
                return Optional.of(taskPanelDto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Optional<TaskDto> getTaskDtoById(String taskId) {
        final String sql = HomeFrameDaoSql.createSqlToGetTaskDtoById();

        try {
            final PreparedStatement preStmt = CONNECTION.prepareStatement(sql);
            preStmt.setString(1, taskId);
            final ResultSet resultSet = preStmt.executeQuery();

            if (resultSet.next()) {
                final TaskDto taskDto = getTaskDtoFromResultSet(resultSet);
                return Optional.of(taskDto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public boolean insertTaskByDto(TaskDto taskDto) {
        final String sql = HomeFrameDaoSql.createSqlToInsertTask();

        try {
            final PreparedStatement preStmt = CONNECTION.prepareStatement(sql);

            preStmt.setString(1, taskDto.getId());
            preStmt.setString(2, taskDto.getParentId());
            preStmt.setString(3, taskDto.getTitle());
            preStmt.setBoolean(4, taskDto.isImportant());
            preStmt.setTimestamp(5, Timestamp.valueOf(taskDto.getStartDateTime()));
            preStmt.setTimestamp(6, Timestamp.valueOf(taskDto.getFinishDateTime()));
            preStmt.setString(7, taskDto.getNote());

            preStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public boolean updateTaskByDto(TaskDto taskDto) {
        final String query = HomeFrameDaoSql.createSqlToUpdateTaskById();

        try {
            final PreparedStatement preStmt = CONNECTION.prepareStatement(query);

            preStmt.setString(1, taskDto.getTitle());
            preStmt.setBoolean(2, taskDto.isImportant());
            preStmt.setTimestamp(3, Timestamp.valueOf(taskDto.getStartDateTime()));
            preStmt.setTimestamp(4, Timestamp.valueOf(taskDto.getFinishDateTime()));
            preStmt.setString(5, taskDto.getNote());
            preStmt.setString(6, taskDto.getId());

            preStmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteTaskById(String taskId) {
        final String sql = HomeFrameDaoSql.createSqlToDeleteTaskById();

        try {
            final PreparedStatement preStmt = CONNECTION.prepareStatement(sql);
            preStmt.setString(1, taskId);

            preStmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
