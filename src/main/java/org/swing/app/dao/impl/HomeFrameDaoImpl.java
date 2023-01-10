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
import java.util.Set;

public class HomeFrameDaoImpl implements HomeFrameDao {

    private static final Logger LOGGER = LogManager.getLogger(HomeFrameDaoImpl.class);

    private final Connection connection;

    public HomeFrameDaoImpl() throws DaoException {
        try {
            this.connection = MySQLConnection.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.error("Create connection failure", e);
            throw new DaoException(e);
        }
    }

    @Override
    public void insertTaskByDto(TaskDto taskDto) throws DaoException {
        final String sql = HomeFrameDaoSql.createSqlToInsertTask();

        try {
            final PreparedStatement preStmt = this.connection.prepareStatement(sql);

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
            final PreparedStatement preStmt = this.connection.prepareStatement(query);

            preStmt.setString(1, taskDto.getTitle());
            preStmt.setBoolean(2, taskDto.isImportant());
            preStmt.setTimestamp(3, Timestamp.valueOf(taskDto.getStartDateTime()));
            preStmt.setTimestamp(4, Timestamp.valueOf(taskDto.getFinishDateTime()));
            preStmt.setTimestamp(5, Timestamp.valueOf(taskDto.getSubmitDateTime()));
            preStmt.setString(6, taskDto.getNote());
            preStmt.setString(7, taskDto.getId());

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
            final PreparedStatement preStmt = this.connection.prepareStatement(sql);
            preStmt.setString(1, taskId);

            preStmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Method: deleteTaskById", e);
            throw new DaoException(e);
        }
    }

    private TaskDto getTaskDtoFromResultSet(ResultSet resultSet) throws SQLException {
        final TaskDto taskDto = new TaskDto();

        taskDto.setId(resultSet.getString("id"));
        taskDto.setParentId(resultSet.getString("parent_id"));
        taskDto.setTitle(resultSet.getString("title"));
        taskDto.setImportant(resultSet.getBoolean("important"));

        final Timestamp startTimestamp = resultSet.getTimestamp("start_datetime");
        taskDto.setStartDateTime(startTimestamp == null ? null : startTimestamp.toLocalDateTime());

        final Timestamp finishTimestamp = resultSet.getTimestamp("finish_datetime");
        taskDto.setFinishDateTime(finishTimestamp == null ? null : finishTimestamp.toLocalDateTime());

        final Timestamp submitTimestamp = resultSet.getTimestamp("submit_datetime");
        taskDto.setSubmitDateTime(submitTimestamp == null ? null : submitTimestamp.toLocalDateTime());

        taskDto.setNote(resultSet.getString("note"));
        return taskDto;
    }

    private TaskPanelDto getTaskPanelDtoFromResultSet(ResultSet resultSet) throws SQLException {
        final TaskPanelDto taskPanelDto = new TaskPanelDto();

        final TaskDto taskDto = getTaskDtoFromResultSet(resultSet);
        taskPanelDto.setTaskDto(taskDto);

        final Timestamp createdAtTimestamp = resultSet.getTimestamp("created_at");
        taskPanelDto.setCreatedAt(createdAtTimestamp == null ? null : createdAtTimestamp.toLocalDateTime());

        final Timestamp updatedAtTimestamp = resultSet.getTimestamp("updated_at");
        taskPanelDto.setUpdatedAt(updatedAtTimestamp == null ? null : updatedAtTimestamp.toLocalDateTime());

        taskPanelDto.setChildCompletedTaskCount(resultSet.getInt("child_task_completed_count"));
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
    public TaskPanelDto getTaskPanelDtoById(String taskId) throws DaoException {
        final String sql = HomeFrameDaoSql.createSqlToGetTaskPanelDtoById();

        try {
            final PreparedStatement preStmt = this.connection.prepareStatement(sql);
            preStmt.setString(1, taskId);

            final ResultSet resultSet = preStmt.executeQuery();
            if (resultSet.next()) {
                return getTaskPanelDtoFromResultSet(resultSet);
            }

            return null;
        } catch (SQLException e) {
            LOGGER.error("Method: getTaskPanelDtoById", e);
            throw new DaoException(e);
        }
    }

    @Override
    public TaskDto getTaskDtoById(String taskId) throws DaoException {
        final String sql = HomeFrameDaoSql.createSqlToGetTaskDtoById();

        try {
            final PreparedStatement preStmt = this.connection.prepareStatement(sql);
            preStmt.setString(1, taskId);
            final ResultSet resultSet = preStmt.executeQuery();

            if (resultSet.next()) {
                return getTaskDtoFromResultSet(resultSet);
            }

            return null;
        } catch (SQLException e) {
            LOGGER.error("Method: getTaskDtoById", e);
            throw new DaoException(e);
        }
    }

    @Override
    public Set<TaskPanelDto> getIncompleteRootTaskPanelDtos() throws DaoException {
        final String sql = HomeFrameDaoSql.createSqlToGetIncompleteRootTaskPanelDto();

        try {
            final PreparedStatement preStmt = this.connection.prepareStatement(sql);
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
            final PreparedStatement preStmt = this.connection.prepareStatement(sql);
            preStmt.setString(1, parentId);

            final ResultSet resultSet = preStmt.executeQuery();
            return getTaskPanelDtosFromResultSet(resultSet);
        } catch (SQLException e) {
            LOGGER.error("Method: getTaskPanelDtosByParentId", e);
            throw new DaoException(e);
        }
    }
}
