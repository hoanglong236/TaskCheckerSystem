package org.swing.app.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.swing.app.dao.HomeFrameDao;
import org.swing.app.dao.connection.MySQLConnection;
import org.swing.app.dao.exception.DaoException;
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

    private static final Logger LOGGER = LogManager.getLogger(HomeFrameDaoImpl.class);

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

        taskPanelDto.setCompleted(resultSet.getBoolean("completed"));
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
    public Set<TaskPanelDto> getIncompleteRootTaskPanelDtos() throws DaoException {
        final String sql = HomeFrameDaoSql.createSqlToGetIncompleteRootTaskPanelDto();

        try {
            final PreparedStatement preStmt = CONNECTION.prepareStatement(sql);
            final ResultSet resultSet = preStmt.executeQuery();

            return getTaskPanelDtosFromResultSet(resultSet);
        } catch (SQLException e) {
            LOGGER.error("Method: getIncompleteRootTaskPanelDtos", e);
            throw new DaoException(e);
        }
    }

    @Override
    public Set<TaskPanelDto> getTaskPanelDtosByParentId(String parentId) throws DaoException {
        final String sql = HomeFrameDaoSql.createSqlToGetTaskPanelDtoByParentId();

        try {
            final PreparedStatement preStmt = CONNECTION.prepareStatement(sql);
            preStmt.setString(1, parentId);

            final ResultSet resultSet = preStmt.executeQuery();
            return getTaskPanelDtosFromResultSet(resultSet);
        } catch (SQLException e) {
            LOGGER.error("Method: getTaskPanelDtosByParentId", e);
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<TaskPanelDto> getTaskPanelDtoById(String taskId) throws DaoException {
        final String sql = HomeFrameDaoSql.createSqlToGetTaskPanelDtoById();

        try {
            final PreparedStatement preStmt = CONNECTION.prepareStatement(sql);
            preStmt.setString(1, taskId);

            final ResultSet resultSet = preStmt.executeQuery();
            if (resultSet.next()) {
                final TaskPanelDto taskPanelDto = getTaskPanelDtoFromResultSet(resultSet);
                return Optional.of(taskPanelDto);
            }

            return Optional.empty();
        } catch (SQLException e) {
            LOGGER.error("Method: getTaskPanelDtoById", e);
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<TaskDto> getTaskDtoById(String taskId) throws DaoException {
        final String sql = HomeFrameDaoSql.createSqlToGetTaskDtoById();

        try {
            final PreparedStatement preStmt = CONNECTION.prepareStatement(sql);
            preStmt.setString(1, taskId);
            final ResultSet resultSet = preStmt.executeQuery();

            if (resultSet.next()) {
                final TaskDto taskDto = getTaskDtoFromResultSet(resultSet);
                return Optional.of(taskDto);
            }

            return Optional.empty();
        } catch (SQLException e) {
            LOGGER.error("Method: getTaskDtoById", e);
            throw new DaoException(e);
        }
    }

    @Override
    public void insertTaskByDto(TaskDto taskDto) throws DaoException {
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
            LOGGER.error("Method: insertTaskByDto", e);
            throw new DaoException(e);
        }
    }

    @Override
    public void updateTaskByDto(TaskDto taskDto) throws DaoException {
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
        } catch (SQLException e) {
            LOGGER.error("Method: updateTaskByDto", e);
            throw new DaoException(e);
        }
    }

    @Override
    public void deleteTaskById(String taskId) throws DaoException {
        final String sql = HomeFrameDaoSql.createSqlToDeleteTaskById();

        try {
            final PreparedStatement preStmt = CONNECTION.prepareStatement(sql);
            preStmt.setString(1, taskId);

            preStmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Method: deleteTaskById", e);
            throw new DaoException(e);
        }
    }
}
